# 昔のゲームのような戦闘を意識したWebアプリ
+ 開発言語: java html
+ 開発環境: eclipse
+ 使用データベース: h2database
## 概要
これは、職業訓練のカリキュラムの一環で制作したものになります。

制作期限など細かい制約をつけて作成したものではなく、かなり自由に作ったものなので表面上は問題なく動作する程度のクオリティです。

本来はPostgreSQLをデータベースに使用する予定でしたが、導入していないPCでも動作することを第一にしたためh2databaseを使用しています。

コードには実装しようとしたが作業時間がかかりすぎることが判明してそのまま放置されたものがいくつかあります。再び、コードを編集する時間が出来れば、それらの実装を行う予定です。

## 画面説明
![スクリーンショット (12)](https://github.com/user-attachments/assets/8e462293-bec0-43d7-bd20-c8186bfa6eb0)
アプリ起動後、ブラウザにこのURLを直接入力することから始まります。

認証機能とセーブ機能の実装を予定しています。「初めから」以外がただのテキストなのはこれらが未実装なためです。
![スクリーンショット (13)](https://github.com/user-attachments/assets/c869d0fd-d0a7-49be-be8b-10f2dce1ab7e)
「初めから」を押した後のキャラクターメイク画面になります。

あなたが操作するキャラクターの名前の入力とポイントを割り振ってステータスを強化します。

名前の未入力、所持ポイント以上の数値をステータスに割り振ったときなどはメッセージを表示して再入力するようにしています。
![スクリーンショット (14)](https://github.com/user-attachments/assets/aaaa9a0a-ca23-48e7-a79a-949adfb9d3f3)
「設定完了」を押した後は入力された内容を反映したものを表示します。
![スクリーンショット (15)](https://github.com/user-attachments/assets/b51a3d4c-666d-4d3f-9ecf-99339692e58f)
「戦闘へ」を押したら戦闘開始です。

「攻撃」「魔法」「防御」「降参」の４つのコマンドを選択して戦っていきます。

魔法の種類などを増やしたい気持ちはありましたが、コマンド選択からさらにコマンド選択を行う方法がその時の知識では出来なかったため、シンプルにしました。
![スクリーンショット (16)](https://github.com/user-attachments/assets/dd11d228-46a9-448e-b53f-2530231ee4d5)
「攻撃」を選択したときの画面です。
![スクリーンショット (17)](https://github.com/user-attachments/assets/0bb86280-cfa9-419e-a466-239fa42a8e1a)
「魔法」を選択したときの画面です。
![スクリーンショット (18)](https://github.com/user-attachments/assets/f56ba933-dfd7-4b38-afa0-cbb14af6f2d9)
「防御」を選択したときの画面です。
![スクリーンショット (19)](https://github.com/user-attachments/assets/2a3a7c05-a0cf-4301-8e3e-092634b7566f)
敵のHPを0以下にしたときの画面です。

リザルト画面へ移ります。
![スクリーンショット (20)](https://github.com/user-attachments/assets/83f4cc57-a826-419f-9b5a-5fd08494ccfe)
ステータスアップ画面です。

最初のキャラクターメイク画面から名前入力をなくしただけのシンプルなものです。

この画面でもポイント以上の数値を割り振ろうとすると再入力のメッセージを表示するようになっています。

以降は延々とループするようになっています。
![スクリーンショット (21)](https://github.com/user-attachments/assets/e468aca0-8f7e-4fdb-989e-d21740f3c1a4)
戦闘で自身のHPが0になったとき、または「降参」したときはタイトル画面へ戻るようになっています。

この時のキャラクターデータを再び使用することはありません。再び使うには同じ名前で同じステータスのキャラクターをもう一度育てるしかありません。

## 注意事項
ブラウザが持つ、前のページに戻る機能や再読み込み機能を使うことを想定しておらず、
使うと正しく動作しないことがあるため、基本的には使わないようにお願いします。
