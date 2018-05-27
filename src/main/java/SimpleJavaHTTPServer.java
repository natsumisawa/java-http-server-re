import utils.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author natsumi.sawa
 */

public  class SimpleHTTPServer {

    private static final Logger logger = new Logger(SimpleHTTPServer.class.simpleName());

    private static final int PORT_NUM = 8000;

    public static void main(String[] args) = {
        try (ServerSocket serverSocket = new SeverSocket) {
        // SeverSocketはネットワーク経由で要求が送られてくるのを待つ
            logger.log("listening HTTP request on localhost port" + PORT_NUM + "...");
            while (true) {
                try (Socket cliantSocket  = serverSocket.accept();
                // acceptはこのソケットに対する要求を待機して受け取る
                printWriter writer = new PrintWriter(cliantSocket.getOutputStream(), true);
                // 既存のOutputStreamから新しいprintwriterを作成する
                BufferReader reader = new BufferReader(new InputStreamReader(cliantSocket.getInputStream())
                // inputStreamはreadするたびにバイトコードを文字に変換する
                // BufferReaderでラップすると効率的に変換できる(readLineっていうメソッドがある)
                        )) {
                    String line;
                    while ((line = reader.readLine() != null & !line.isEmpty())) {
                        logger.log(line);
                        writer.println(line);
                        // そのまま返す
                    }
                } catch (IOException e) {
                    // readLineがはく可能性がある例外
                    logger.error("クライアントとの通信時にエラーが発生しました errorMessage: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            logger.error("サーバーソケットの確立に失敗しました errorMessage: " + e.getMessage);
        }
    }
}