package tqs5.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@org.springframework.stereotype.Service
public class AppService {
	private static Logger logger = LogManager.getLogger(AppService.class);
	private API api = new API();
	private Cache cache = new Cache();

	public List<String> getCountries() throws IOException, InterruptedException {
		String text = api.getAllCountries();
		text = text.substring(text.lastIndexOf("[")+2, text.lastIndexOf("]")-1);
		return new ArrayList<>(Arrays.asList(text.split("\",\"")));
    }

    public List<String> getCountries(String country) throws IOException, InterruptedException {
		String text = api.getCountries(country);
		text = text.substring(text.lastIndexOf("[")+2, text.lastIndexOf("]")-1);
		return new ArrayList<>(Arrays.asList(text.split("\",\"")));
    }

    public String getCountryStats(String country) throws IOException, InterruptedException {
		String stored = cache.checkCache(country);
		logger.info("Checking cache for "+country+"...");
		if (stored.equals("")) {
			logger.info("Information about "+country+" not found in the cache. Checking API...");
			String response = api.getCountryStats(country);
			ArrayList<String> list = new ArrayList<>();
			list.add(response);
			list.add(String.valueOf(Instant.now().getEpochSecond()));
			cache.put(country, list);
			logger.info(response);
			return response;
		}
		logger.info("Information about "+country+" found in the cache.");
		logger.info(stored);
		return stored;
	}

	public List<String> getCacheStats() {
		List<String> cacheStats = new ArrayList<>();
		cacheStats.add("Cache Requests: "+cache.getCacheRequests());
		cacheStats.add("Cache Hits: "+cache.getCacheHits());
		cacheStats.add("Cache Misses: "+cache.getCacheMiss());
		return cacheStats;
	}
}
