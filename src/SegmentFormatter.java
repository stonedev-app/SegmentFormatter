import java.util.List;
import java.util.stream.IntStream;

public class SegmentFormatter {

    /**
     * 文字列リストに区切り文字をして文字列生成
     * @param argStrList 文字列リスト
     * @param argDelimiter 区切り文字
     * @return 区切り文字を追加した文字列
     */
    public static String addDelimiter(List<String> argStrList, String argDelimiter) {
        // 区切り文字を追加する
        return argStrList
                .stream()
                .reduce("",
                        (sum, elm) -> {
                            // 空文字の場合は先頭なので区切り文字追加なし
                            if (sum.isEmpty()) {
                                return sum + elm;
                            }
                            // 先頭以外は区切り文字追加
                            else {
                                return sum + argDelimiter + elm;
                            }
                        });
    }

    /**
     * 文字列の並びを反転する
     * @param argStr 文字列
     * @return 反転した文字列
     */
    public static String reverse(String argStr) {
        return new StringBuilder(argStr)
                .reverse()          // 反転
                .toString();        // 文字列に変換
    }

    /**
     * N文字ずつのListを返却する
     *
     * @param argStr 文字列
     * @param argN N文字区切り
     * @return N文字ずつのList
     */
    public static List<String> splitByN(String argStr, int argN) {
        int len = argStr.length();
        return IntStream.range(0, (len + (argN - 1)) / argN)  // n文字区切りなので、切り上げて個数計算
                .mapToObj(i -> {
                    int start = i * argN;                  // 切り出し開始インデックス
                    int end = Math.min(start + argN, len); // 切り出し終了インデックス
                    return argStr.substring(start, end);   // 切り出し
                })
                .toList();                              // イミュータブルなリストに変換
    }
}
