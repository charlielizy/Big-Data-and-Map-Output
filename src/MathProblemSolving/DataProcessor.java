package MathProblemSolving;

/**
 * Class DataProcessor is for calculating all combination of a large numbers of Double type numbers. 
 * The rules are as following:
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class DataProcessor {

    private static Logger logger = Logger.getLogger(DataProcessor.class);
    private Long maxNum = 1000000000000L;
    private static Integer MAXINDEX_IN_DATAFILE = 1000000;
    private static Integer MAXINDEX_IN_CALCULATEFILE = 100000000;
    private static Long index = -1L;
    private Long dataFileIndex = 1L;
    private static String FILE_PATH = "C:\\Temp";
    private Map<Long, Double> valueMap;
    Double[] calculateArray = new Double[MAXINDEX_IN_CALCULATEFILE];
    private List<Double> dataArray = new ArrayList<Double>();
    long numberCalculating;
    Double result = 0d;
    Double multiResult = 1d;

    /**
     * get n in m numbers which save under c:\\temp and named as numbers.json,
     * calculate the multiplication and return the sum up result of all those
     * multiplications. valueMap is the collection of current numbers, if cannot
     * find the number of right location, then read the related file into
     * valueMap calculateArray is the collection of n location, which saved as
     * "calcuted*.json" under c:\\temp
     *
     * @param m
     * @param n
     * @return
     */
    private double comMultiplication(long m, long n) {
        long i, j;

        for (i = m; i >= n; i--) {

            if (null == this.valueMap || null == valueMap.get(i)) {
                double temp_ceil = ((double) i) / MAXINDEX_IN_DATAFILE;
                long temp_datafile_index = (long) Math.ceil(temp_ceil);

                valueMap = FileUtil.getValueListByFile(FILE_PATH + "\\" + temp_datafile_index + ".json", MAXINDEX_IN_DATAFILE);
            }
            Integer modindex = (int) (n % MAXINDEX_IN_CALCULATEFILE);
            long temp_calculatefile_index = n / MAXINDEX_IN_CALCULATEFILE;
            calculateArray[modindex] = valueMap.get(i);
            if (modindex == 0 && n > 1) {
                FileUtil.saveToJsonFile(Arrays.asList(calculateArray), FILE_PATH + "\\calculate" + temp_calculatefile_index + ".json");
            }

            if (n > 1) {
                comMultiplication(i - 1, n - 1);
            } else {
                for (j = 1; j <= numberCalculating; j++) {

                    long temp_file_index_end = j / MAXINDEX_IN_CALCULATEFILE;
                    Integer temp_index_end = (int) (j % MAXINDEX_IN_CALCULATEFILE);
                    if (temp_file_index_end > 0) {
                        calculateArray = FileUtil.getCalcuMapByFile(FILE_PATH + "\\calculate" + temp_file_index_end + ".json", MAXINDEX_IN_CALCULATEFILE);
                    }
//					System.out.print(calculateArray[temp_index_end]);
                    multiResult = multiResult * calculateArray[temp_index_end];
                }
//				System.out.println("Multi"+multiResult);
                result = result + multiResult;

                multiResult = 1d;
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
    	logger.info("Running DataProcessor.");
        DataProcessor dataService = new DataProcessor();
        dataService.userEnter();
//		dataService.AutomatedEnter();
        Double result = dataService.calculateFromFile();
    }

    public void AutomatedEnter() {
        System.out.println("Automated enter number 1-8.");
        int i = 0;
        while (i < 8) {
            i++;
            putUserEnterToArray((double) i);
        }
        if (this.dataArray.size() > 0) {
            FileUtil.saveToJsonFile(this.dataArray, FILE_PATH + "\\" + this.dataFileIndex + ".json");
            this.dataArray.clear();
        }
    }
    /**
     * Accept user enter from console.
     */
    public void userEnter() {
        String userEnter = null;
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter double type number, 'exit' to stop running.");

        while (!(userEnter = input.next()).equalsIgnoreCase("exit")) {

            Double doubleValue = validateUserEnter(userEnter);
            if ((null != doubleValue) && (putUserEnterToArray(doubleValue))) {
                System.out.println("'" + userEnter + "' entered. Next please. ");
            } else {
                System.out.println("Double type required, enter again. ");
            }

        }
        if (this.dataArray.size() > 0 && FileUtil.saveToJsonFile(this.dataArray, FILE_PATH + "\\" + this.dataFileIndex + ".json")) {
            this.dataArray.clear();
        }
    }
    /**
     * Loop to send 'n' to comMultiplication.
     * @return 
     */
    public Double calculateFromFile() {
        if (null == dataFileIndex || dataFileIndex <= 0) {
            return 0d;
        }
        Double resultMultiByNum = 0d;
        valueMap = FileUtil.getValueListByFile(FILE_PATH + "\\" + dataFileIndex + ".json", MAXINDEX_IN_DATAFILE);
        System.out.print("Equation:\n" + resultMultiByNum);
        for (long i = 1; i <= index + 1; i++) {
            numberCalculating = i;
            dataArray.clear();
            result = 0d;
            Double resultOfI = comMultiplication(index + 1, i);
            if (i % 6 == 0) {
                System.out.println();
            }
            if (0 != (i % 2)) {

                System.out.print(" + ");

                resultMultiByNum = resultMultiByNum + resultOfI;

            } else {
                System.out.print(" - ");
                resultMultiByNum = resultMultiByNum - resultOfI;

            }
            System.out.print(resultOfI);
        }
        System.out.println(" = " + resultMultiByNum + ". \nThanks~");
        return resultMultiByNum;
    }
    
    /**
     * Put the number that user entered to an Array. if the array is full, it will be save to the path on disk.
     * @param doubleValue
     * @return 
     */
    private boolean putUserEnterToArray(Double doubleValue) {
        index++;
        this.dataArray.add(doubleValue);
        Long modIndex = (index + 1) % MAXINDEX_IN_DATAFILE;
        if (modIndex == 0 && index != 0) {
            if (FileUtil.saveToJsonFile(this.dataArray, FILE_PATH + "\\" + dataFileIndex + ".json")) {
                this.dataFileIndex++;
                this.dataArray.clear();
                return true;
            }

        }
        return false;
    }

    /**
     * validate what user entered. 
     * @param userEnter
     * @return if pass the validation, return the number, or return null
     */
    private static Double validateUserEnter(String userEnter) {
        try {
            return Double.valueOf(userEnter);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

}
