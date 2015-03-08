package cz.bsc.homework.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

import cz.bsc.homework.console.Console;
import cz.bsc.homework.domain.Payment;

/**
 * Reads exchange rates from cnb
 * 
 * @author dolezelt
 *
 */
public class CNBExchangeRatesService implements ExchangeRatesService{

	public static final String CNB_URL = "http://www.cnb.cz/en/financial_markets/foreign_exchange_market/exchange_rate_fixing/daily.txt";

	private Map<String, Double> rateCache;

	@Override
	public Double getRate(Payment payment) {
		if (rateCache == null) {
			initRates();
			
		}
		return rateCache.get(payment.getCurrency());
	}

	//tohle by bylo lepsi jako scheduled task, ale bohuzel uz nemam cas
	private void initRates() {
		rateCache= new HashMap<String, Double>();
		URL website;
		try {
			website = new URL(CNB_URL);
			try (InputStream is = website.openStream();) {
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				IOUtils.copy(is, byteArrayOutputStream);

				BufferedReader in = new BufferedReader(new StringReader(
						new String(byteArrayOutputStream.toByteArray())));
				String line = null;

				int row = 0;
				while ((line = in.readLine()) != null) {
					if (row > 2) {
						String[] rowData = line.split("\\|");

						double amount = Double.parseDouble(rowData[2]);
						String code = rowData[3];
						double rate = Double.parseDouble(rowData[4]) / amount;
						rateCache.put(code, rate);
					}
					row++;
				}

				// to USD rate
				if (rateCache.get("USD") != null) {
					Double usdRate = rateCache.get("USD");
					for (String key : rateCache.keySet()) {
						rateCache.put(key, rateCache.get(key) / usdRate);
					}
					rateCache.put("CZK",1/ usdRate);
				}
					
				

			} catch (Exception e) {
				Console.getInstance().print(e);
			}
		} catch (MalformedURLException e) {
			Console.getInstance().print(e);
		}

	}
}
