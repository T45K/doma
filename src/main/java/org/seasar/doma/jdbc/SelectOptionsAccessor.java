package org.seasar.doma.jdbc;

/**
 * {@link SelectOptions} へのアクセッサーです。
 *
 * <p>フレームワークが使用します。
 *
 * @author taedium
 */
public class SelectOptionsAccessor {

  /**
   * オプションから集計するかどうかを返します。
   *
   * @param options オプション
   * @return 集計するかどうか
   */
  public static boolean isCount(SelectOptions options) {
    return options.count;
  }

  /**
   * オプションに集計サイズを設定します。
   *
   * @param options オプション
   * @param countSize 集計サイズ
   */
  public static void setCountSize(SelectOptions options, long countSize) {
    options.countSize = countSize;
  }

  /**
   * オプションから悲観的排他制御の種別を返します。
   *
   * @param options オプション
   * @return 悲観的排他制御の種別
   */
  public static SelectForUpdateType getForUpdateType(SelectOptions options) {
    return options.forUpdateType;
  }

  /**
   * オプションから悲観的排他制御のロック取得の待機時間を返します。
   *
   * @param options オプション
   * @return 待機時間（秒）
   */
  public static int getWaitSeconds(SelectOptions options) {
    return options.waitSeconds;
  }

  /**
   * オプションからロック対象のテーブルもしくはカラムのエイリアスの配列を返します。
   *
   * @param options オプション
   * @return テーブルもしくはカラムのエイリアスの配列
   */
  public static String[] getAliases(SelectOptions options) {
    return options.aliases;
  }

  /**
   * オプションからページングのオフセットを返します。
   *
   * @param options オプション
   * @return オフセット
   */
  public static long getOffset(SelectOptions options) {
    return options.offset;
  }

  /**
   * オプションからページングのリミットを返します。
   *
   * @param options オプション
   * @return リミット
   */
  public static long getLimit(SelectOptions options) {
    return options.limit;
  }
}
