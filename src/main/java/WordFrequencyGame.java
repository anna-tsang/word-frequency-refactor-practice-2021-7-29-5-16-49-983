import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

// i. naming 2. magic string 3. temp var 4. for loop 5. long method 6. if/else
public class WordFrequencyGame {

    public static final String SPACE_PATTERN = "\\s+";

    public String getResult(String inputWords){


        if (inputWords.split(SPACE_PATTERN).length==1) {
            return inputWords + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] words = inputWords.split(SPACE_PATTERN);

                List<WordsInfo> inputWordsList = new ArrayList<>();
                for (String s : words) {
                    WordsInfo input = new WordsInfo(s, 1);
                    inputWordsList.add(input);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordsInfo>> map =getListMap(inputWordsList);

                List<WordsInfo> list = new ArrayList<>();
                for (Map.Entry<String, List<WordsInfo>> entry : map.entrySet()){
                    WordsInfo input = new WordsInfo(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                inputWordsList = list;

                inputWordsList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordsInfo w : inputWordsList) {
                    String s = w.getValue() + " " +w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }


    private Map<String,List<WordsInfo>> getListMap(List<WordsInfo> inputList) {
        Map<String, List<WordsInfo>> map = new HashMap<>();
        for (WordsInfo input :  inputList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())){
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            }

            else {
                map.get(input.getValue()).add(input);
            }
        }


        return map;
    }


}
