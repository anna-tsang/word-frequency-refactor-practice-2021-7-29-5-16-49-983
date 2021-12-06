import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

// i. naming 2. magic string 3. temp var 4. for loop 5. long method 6. if/else
public class WordFrequencyGame {

    public static final String SPACE_PATTERN = "\\s+";

    public String getResult(String inputWords) {


        if (inputWords.split(SPACE_PATTERN).length == 1) {
            return inputWords + " 1";
        } else {

            try {
                List<WordsInfo> inputWordsList = calculateWordFrequency(inputWords);

                inputWordsList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordsInfo w : inputWordsList) {
                    String s = w.getValue() + " " + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }
    private List<WordsInfo> calculateWordFrequency(String sentences){
        List<String> words = Arrays.asList(sentences.split(SPACE_PATTERN));
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());
        List<WordsInfo> wordInfos = new ArrayList<>();
        distinctWords.forEach(distinctWord ->{
            int frequency = (int) words.stream().filter(word -> word.equals((distinctWord))).count();
            WordsInfo wordInfo = new WordsInfo(distinctWord, frequency);
            wordInfos.add(wordInfo);
        });
        return wordInfos;
    }


    private Map<String, List<WordsInfo>> getListMap(List<WordsInfo> inputList) {
        Map<String, List<WordsInfo>> map = new HashMap<>();
        for (WordsInfo input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }


        return map;
    }


}
