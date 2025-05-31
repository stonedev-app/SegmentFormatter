public class Main {
    public static void main(String[] args) {

        // フォーマット対象文字列
        String str = "1234568";
        System.out.println(str);

        // 区切り文字フォーマット処理を生成
        var formatter = SegmentFormat.createFormatter(
                "-",
                2,
                true);

        // ハイフンを右から2文字毎に追加
        var resultStr = formatter.apply(str);

        // ハイフン追加後文字列
        System.out.println(resultStr);
    }
}