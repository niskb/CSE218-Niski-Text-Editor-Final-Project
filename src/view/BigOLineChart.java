package view;

import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import model.MasterLinkList;
import utils.Counter;
import utils.MasterLinkListBuilder;
import utils.ParagraphGenerator;

public class BigOLineChart {

	private static NumberAxis xAxis = new NumberAxis();
	private static NumberAxis yAxis = new NumberAxis();
	private static LineChart<Number, Number> lineChart;

	private static void setUpLineChart() {
		xAxis.setLabel("Number of Words");
		yAxis.setLabel("Time (ms)");
		lineChart = new LineChart<Number, Number>(xAxis, yAxis);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static LineChart generateOneLoopChart() {
		setUpLineChart();
		lineChart.setTitle("One Loop");
		MasterLinkList theMasterList = MasterLinkListBuilder
				.buildMasterLinkList(ParagraphGenerator.wordsSeperator(SceneBuilder.getTheTextArea().getText()));
		String words100 = ParagraphGenerator.generateParagraphNoOutput(theMasterList, 100);
		String words1000 = ParagraphGenerator.generateParagraphNoOutput(theMasterList, 1000);
		String words10000 = ParagraphGenerator.generateParagraphNoOutput(theMasterList, 10000);
		String words100000 = ParagraphGenerator.generateParagraphNoOutput(theMasterList, 100000);
		String[] generatedParagraphs = new String[] { words100, words1000, words10000, words100000 };
		XYChart.Series<Number, Number> series = new Series<Number, Number>();
		int i = 0;
		for (int numberOfWords = 100; numberOfWords <= 100000; numberOfWords = numberOfWords * 10) {
			long calculatedTime = calculateTimeForOneLoop(generatedParagraphs[i++]);
			series.getData().add(new XYChart.Data(numberOfWords, calculatedTime));
			outputDataForOneLoop(numberOfWords, calculatedTime);
		}
		series.setName("One Loop Involved");
		lineChart.getData().add(series);
		return lineChart;
	}

	private static long calculateTimeForOneLoop(String generatedParagraph) {
		long start = System.currentTimeMillis();
		Counter.countWords(generatedParagraph);
		Counter.countSentences(generatedParagraph);
		Counter.countSyllables(generatedParagraph);
		return System.currentTimeMillis() - start;
	}

	private static void outputDataForOneLoop(int numberOfWords, long calculatedTime) {
		try {
			FileWriter fw = new FileWriter("outputData\\LoopOutput.txt", true);
			fw.write("One Loop: Number of Words: " + numberOfWords + ": Time: " + calculatedTime);
			fw.write("\n");
			fw.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static LineChart generateThreeLoopsChart() {
		setUpLineChart();
		lineChart.setTitle("Three Loops");
		MasterLinkList theMasterList = MasterLinkListBuilder
				.buildMasterLinkList(ParagraphGenerator.wordsSeperator(SceneBuilder.getTheTextArea().getText()));
		String words100 = ParagraphGenerator.generateParagraphNoOutput(theMasterList, 100);
		String words1000 = ParagraphGenerator.generateParagraphNoOutput(theMasterList, 1000);
		String words10000 = ParagraphGenerator.generateParagraphNoOutput(theMasterList, 10000);
		String words100000 = ParagraphGenerator.generateParagraphNoOutput(theMasterList, 100000);
		String[] generatedParagraphs = new String[] { words100, words1000, words10000, words100000 };
		XYChart.Series<Number, Number> series = new Series<Number, Number>();
		long[] totalsOfTime = new long[4];
		// Count Words
		int i = 0;
		for (int numberOfWords = 100; numberOfWords <= 100000; numberOfWords = numberOfWords * 10) {
			long calculatedTime = calcaluteTimeForWordCount(generatedParagraphs[i++]);
			placeTimeIntoArray(totalsOfTime, numberOfWords, calculatedTime);
		}
		// Count Sentences
		i = 0;
		for (int numberOfWords = 100; numberOfWords <= 100000; numberOfWords = numberOfWords * 10) {
			long calculatedTime = calcaluteTimeForSentenceCount(generatedParagraphs[i++]);
			placeTimeIntoArray(totalsOfTime, numberOfWords, calculatedTime);
		}
		// Count Syllables
		i = 0;
		for (int numberOfWords = 100; numberOfWords <= 100000; numberOfWords = numberOfWords * 10) {
			long calculatedTime = calcaluteTimeForSyllableCount(generatedParagraphs[i++]);
			placeTimeIntoArray(totalsOfTime, numberOfWords, calculatedTime);
		}
		series.getData().add(new XYChart.Data(100, totalsOfTime[0]));
		series.getData().add(new XYChart.Data(1000, totalsOfTime[1]));
		series.getData().add(new XYChart.Data(10000, totalsOfTime[2]));
		series.getData().add(new XYChart.Data(100000, totalsOfTime[3]));
		outputDataForThreeLoops(totalsOfTime);
		series.setName("Three Loops Involved");
		lineChart.getData().add(series);
		return lineChart;
	}

	private static long calcaluteTimeForWordCount(String generatedParagraph) {
		long start = System.currentTimeMillis();
		Counter.countWords(generatedParagraph);
		return System.currentTimeMillis() - start;
	}

	private static long calcaluteTimeForSentenceCount(String generatedParagraph) {
		long start = System.currentTimeMillis();
		Counter.countSentences(generatedParagraph);
		return System.currentTimeMillis() - start;
	}

	private static long calcaluteTimeForSyllableCount(String generatedParagraph) {
		long start = System.currentTimeMillis();
		Counter.countSyllables(generatedParagraph);
		return System.currentTimeMillis() - start;
	}

	private static void placeTimeIntoArray(long[] totalsOfTime, int numberOfWords, long calculatedTime) {
		if (numberOfWords == 100)
			totalsOfTime[0] += calculatedTime;
		else if (numberOfWords == 1000)
			totalsOfTime[1] += calculatedTime;
		else if (numberOfWords == 10000)
			totalsOfTime[2] += calculatedTime;
		else if (numberOfWords == 100000)
			totalsOfTime[3] += calculatedTime;
	}

	private static void outputDataForThreeLoops(long[] totalsOfTime) {
		try {
			FileWriter fw = new FileWriter("outputData\\LoopOutput.txt", true);
			fw.write("Three Loop: Number of Words: " + 100 + ": Time: " + totalsOfTime[0]);
			fw.write("\n");
			fw.write("Three Loop: Number of Words: " + 1000 + ": Time: " + totalsOfTime[1]);
			fw.write("\n");
			fw.write("Three Loop: Number of Words: " + 10000 + ": Time: " + totalsOfTime[2]);
			fw.write("\n");
			fw.write("Three Loop: Number of Words: " + 100000 + ": Time: " + totalsOfTime[3]);
			fw.write("\n");
			fw.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}

}
