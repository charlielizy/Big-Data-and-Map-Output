package MathProblemSolving;


import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.apache.log4j.Logger;


public class FileUtil {

	
	protected final static Logger logger = Logger.getLogger(FileUtil.class);

	public static Map<Long, Double> getValueListByFile(String filePath, Integer maxFileSize) {
		File file = new File(filePath);
		if (!file.exists()) {
			logger.error("File not exist." + filePath + " Error happened in FileUtil getValueListByFile method ");
			return null;
		}
		try (RandomAccessFile reader = new RandomAccessFile(file, "r");) {
			int bufferSize = 4800 * 100;
			byte[] dataBuf = new byte[bufferSize];
			Long pos = 0l;
			Map<Long, Double> result = new HashMap<>();
			while (pos < reader.length()) {
				reader.read(dataBuf, 0, dataBuf.length);
				String resultFromBuffer = new String(dataBuf);
				String[] doubleValue = resultFromBuffer.trim().split(" ");

				int fileName = Integer.valueOf(file.getName().substring(0, file.getName().indexOf(".json")));
				long i = 0;
				for (String value : doubleValue) {
					i++;
					result.put((fileName - 1L) * maxFileSize + i, Double.valueOf(value));
				}
				reader.seek(bufferSize);
				pos += bufferSize;
			}

			return result;
		} catch (Exception e) {
			logger.error("Read File error." + filePath + " Error happened in FileUtil getValueListByFile method ");
			return null;
		}

	}

	public static Double[] getCalcuMapByFile(String filePath, Integer max_Index) {
		File file = new File(filePath);
		if (!file.exists()) {
			logger.error("File not exist." + filePath + " Error happened in FileUtil getCalcuMapByFile method ");
			return null;
		}
		try (RandomAccessFile reader = new RandomAccessFile(file, "r");) {
			int bufferSize = 4800 * 100;
			byte[] dataBuf = new byte[bufferSize];
			Long pos = 0l;
			Double[] result = new Double[max_Index];
			while (pos < reader.length()) {
				reader.read(dataBuf, 0, dataBuf.length);
				String resultFromBuffer = new String(dataBuf);
				String[] doubleValue = resultFromBuffer.trim().split(" ");

				int i = 0;
				for (String value : doubleValue) {

					result[i] = Double.valueOf(value);
					i++;
				}
				reader.seek(bufferSize);
				pos += bufferSize;
			}

			return result;
		} catch (Exception e) {
			logger.error("Read File error." + filePath + " Error happened in FileUtil getCalcuMapByFile method ");
                        logger.error(e);
			return null;
		}

	}

	public static boolean saveToJsonFile(List<Double> dataArray, String filePath) {
		File file = new File(filePath);
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e1) {
				logger.error("File not exist and create failed." + filePath + " Error happened in FileUtil saveToJsonFile method ");
                                logger.error(e1);
				return false;
			}
		try (FileOutputStream fos = new FileOutputStream(file); FileChannel fc = fos.getChannel();) {
			int bufferSize = 4800 * 100;
			ByteBuffer bbuf = ByteBuffer.allocate(bufferSize);
			byte[] datas = null;
			StringBuilder sBuilder = new StringBuilder();
			for (Double doubleValue : dataArray) {

				String str = doubleValue + " ";
				sBuilder.append(str);

				while (sBuilder.toString().length() > 10000) {

					datas = sBuilder.toString().getBytes();
					bbuf.put(datas);
					bbuf.flip();
					fc.write(bbuf);
					bbuf.clear();
					sBuilder.delete(0, sBuilder.toString().length());
				}

			}

			if (sBuilder.toString().length() > 0) {
				datas = sBuilder.toString().getBytes();
				bbuf.put(datas);
				bbuf.flip();
				fc.write(bbuf);
				bbuf.clear();
				sBuilder.delete(0, sBuilder.toString().length());
			}
			return true;
		} catch (FileNotFoundException e) {
			logger.error("File not found." + filePath + " Error happened in FileUtil saveToJsonFile method ");
			logger.error(e);
		} catch (IOException e) {
			logger.error("File IO error." + filePath + " Error happened in FileUtil saveToJsonFile method ");
			logger.error(e);
		}
		return false;
	}
	
}