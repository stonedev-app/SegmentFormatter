import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        // 対象文字列
        String str = "1234568";
        System.out.println(str);

        // 対象文字列を反転させる
        String reverseStr = new StringBuilder(str)
                .reverse()          // 反転
                .toString();        // 文字列に変換

        // N文字ずつの文字列リストに変換する
        List<String> strList = splitByN(reverseStr,2 );

        // ハイフンを追加する
        String addHyphenStr = strList
                .stream()
                .reduce("",
                        (ass, s) -> {
                    // 空文字の場合は先頭なのでハイフン追加しない
                    if (ass.isEmpty()) {
                        return ass + s;
                        // 先頭以外はハイフン追加
                    } else {
                        return ass + "-" + s;
                    }
                });

        // 再度反転して元の並びに戻す
        String resultStr = new StringBuilder(addHyphenStr)
                .reverse()          // 反転
                .toString();        // 文字列に変換

        // ハイフン追加後文字列
        System.out.println(resultStr);

    }

    /**
     * N文字ずつのListを返却する
     *
     * @param str input文字列
     * @param n   N文字区切り
     * @return N文字ずつのList
     */
    public static List<String> splitByN(String str, int n) {
        int len = str.length();
        return IntStream.range(0, (len + (n - 1)) / n)  // n文字区切りなので、切り上げて個数計算
                .mapToObj(i -> {
                    int start = i * n;                  // 切り出し開始インデックス
                    int end = Math.min(start + n, len); // 切り出し終了インデックス
                    return str.substring(start, end);   // 切り出し
                })
                .toList();                              // イミュータブルなリストに変換
    }
}