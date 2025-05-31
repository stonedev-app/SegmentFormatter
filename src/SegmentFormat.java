import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class SegmentFormat {

    /**
     * 区切り文字フォーマット処理を生成する
     * @param argDelimiter 区切り文字
     * @param argEveryCharsNum N文字毎
     * @param argIsFromRight 右から区切り文字追加
     * @return 区切り文字フォーマット処理
     */
    public static Function<String, String> createFormatter(
            String argDelimiter,
            int argEveryCharsNum,
            boolean argIsFromRight) {

        // 設定値のチェックを行う
        // 区切り文字のチェック
        if (argDelimiter == null || argDelimiter.isEmpty()) {
            throw new SegmentFormatException("区切り文字が設定されていません");
        }
        // N文字毎の文字数チェック
        if (argEveryCharsNum < 1) {
            throw new SegmentFormatException("N文字毎には1以上を設定してください");
        }

        // 区切り文字追加処理を返却する
        return (String s) -> {

            // フォーマットする文字列がnull又は空文字の場合
            if (s == null || s.isEmpty()) {
                return s;
            }

            //　フォーマットする文字列
            String target;
            // 右から区切り文字を追加する場合
            if (argIsFromRight) {
                // フォーマット対象文字列を反転する
                target = SegmentFormat.reverse(s);
            }
            // 左から区切り文字を追加する場合
            else {
                // フォーマット対象文字列を設定
                target = s;
            }
            // N文字ずつの文字列リストに変換する
            List<String> strNCharsList = SegmentFormat.splitByN(target,argEveryCharsNum);
            // 区切り文字を追加する
            String addedDelimiterStr = SegmentFormat.addDelimiter(strNCharsList, argDelimiter);
            // フォーマット後文字列
            String formattedStr;
            // 右から区切り文字を追加する場合
            if (argIsFromRight) {
                // フォーマット後文字列を再度反転させて元の並びに戻す
                formattedStr = SegmentFormat.reverse(addedDelimiterStr);
            }
            // 左から区切り文字を追加する場合
            else {
                // フォーマット後文字列を設定
                formattedStr = addedDelimiterStr;
            }
            // フォーマット後文字列を返却
            return formattedStr;
        };
    }

    /**
     * 文字列リストに区切り文字をして文字列生成
     * @param argStrList 文字列リスト
     * @param argDelimiter 区切り文字
     * @return 区切り文字を追加した文字列
     */
    private static String addDelimiter(List<String> argStrList, String argDelimiter) {
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
    private static String reverse(String argStr) {
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
    private static List<String> splitByN(String argStr, int argN) {
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
