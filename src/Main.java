public class Main {
    public static void main(String[] args) {

        // フォーマット対象文字列
        String str = "1234567";
        // フォーマット前文字列を出力
        System.out.printf("フォーマット前文字列:%s%n", str);

        // 区切り文字フォーマット処理を生成
        // 区切り文字:-
        // 何文字毎に区切り文字追加:2
        // 右から区切り文字追加:true
        var formatter = SegmentFormat.createFormatter(
                "-",
                2,
                true);

        // フォーマット処理実行
        var resultStr = formatter.apply(str);

        // フォーマット後文字列を出力
        // 出力結果:1-23-45-67
        System.out.printf("フォーマット後文字列:%s%n", resultStr);
    }
}