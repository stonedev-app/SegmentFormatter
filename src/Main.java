import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 対象文字列
        String str = "1234568";
        System.out.println(str);

        // 対象文字列を反転させる
        String reverseStr = SegmentFormatter.reverse(str);
        // N文字ずつの文字列リストに変換する
        List<String> strList = SegmentFormatter.splitByN(reverseStr,2 );
        // ハイフンを追加する
        String addHyphenStr = SegmentFormatter.addDelimiter(strList, "-");
        // 再度反転して元の並びに戻す
        String resultStr = SegmentFormatter.reverse(addHyphenStr);

        // ハイフン追加後文字列
        System.out.println(resultStr);

    }
}