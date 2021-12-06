import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

// i. naming 2. magic string  4. for loop 5. long method 6. if/else
public class WordFrequencyGame {

    public static final String SPACE_PATTERN = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String inputWords) {

        try {
            List<WordInfo> inputWordsList = calculateWordFrequency(inputWords);
            inputWordsList.sort((w1, w2) -> w2.getFrequency() - w1.getFrequency());
            return getString(inputWordsList);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private List<WordInfo> calculateWordFrequency(String sentences) {
        List<String> words = Arrays.asList(sentences.split(SPACE_PATTERN));
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());
        List<WordInfo> wordInfos = new ArrayList<>();
        distinctWords.forEach(distinctWord -> {
            int frequency = (int) words.stream().filter(word -> word.equals((distinctWord))).count();
            WordInfo wordInfo = new WordInfo(distinctWord, frequency);
            wordInfos.add(wordInfo);
        });
        return wordInfos;
    }

    private String getString(List<WordInfo> wordInfoList) {
        StringJoiner joiner = new StringJoiner("\n");
        wordInfoList.forEach((wordInfo) -> {
            String resultString = wordInfo.getWord() + " " + wordInfo.getFrequency();
            joiner.add(resultString);
        });
        return joiner.toString();
    }



}
