import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

// i. naming 2. magic string  4. for loop 5. long method 6. if/else
public class WordFrequencyGame {

    public static final String SPACE_PATTERN = "\\s+";

    public String getResult(String inputWords) {

        try {
            List<WordsInfo> inputWordsList = calculateWordFrequency(inputWords);
            inputWordsList.sort((w1, w2) -> w2.getFrequency() - w1.getFrequency());
            return getString(inputWordsList);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private List<WordsInfo> calculateWordFrequency(String sentences) {
        List<String> words = Arrays.asList(sentences.split(SPACE_PATTERN));
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());
        List<WordsInfo> wordInfos = new ArrayList<>();
        distinctWords.forEach(distinctWord -> {
            int frequency = (int) words.stream().filter(word -> word.equals((distinctWord))).count();
            WordsInfo wordInfo = new WordsInfo(distinctWord, frequency);
            wordInfos.add(wordInfo);
        });
        return wordInfos;
    }

    private String getString(List<WordsInfo> wordInfoList) {
        StringJoiner joiner = new StringJoiner("\n");
        for (WordsInfo wordInfo : wordInfoList) {
            String s = wordInfo.getWord() + " " + wordInfo.getFrequency();
            joiner.add(s);
        }
        return joiner.toString();
    }



}
