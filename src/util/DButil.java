package util;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

public class DButil {

//    public static void main(String[] args) throws IOException {
//        InputStream inputStream = new FileInputStream(new File("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\util\\db.properties"));
//        Reader reader = new InputStreamReader(inputStream, "utf-8");
//        BufferedReader bufferedReader = new BufferedReader(reader);
//        String s = bufferedReader.readLine();
//        int i = 0;
//        String[] ss = new String[4];
//        while (s!=null){
//            String[] st = s.split("=");
//            ss[i] = st[1];
//            i=i+1;
//            System.out.println(st[1]);
//            s = bufferedReader.readLine();
//        }
//
//        bufferedReader.close();
//        inputStream.close();
//
//        for (int j=0;j<ss.length;j++){
//            System.out.println(ss[j]);
//        }
//    }

    //实例化连接池
    public static Vector<Connection> connectionPool = new Vector<Connection>();

    //静态代码块，在类加载的时候执行，执行在主方法执行之前
    static {
        try {

            InputStream inputStream = new FileInputStream(new File("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\util\\db.properties"));
            Reader reader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String s = bufferedReader.readLine();
            int i = 0;
            String[] ss = new String[4];
            while (s!=null){
                String[] st = s.split("=");
                ss[i] = st[1];
                i=i+1;
                System.out.println(st[1]);
                s = bufferedReader.readLine();
            }

            bufferedReader.close();
            inputStream.close();

            Class.forName(ss[2]);
            for (int j = 0; j < 10; j++) {
                Connection connection = DriverManager.getConnection(ss[3],ss[0], ss[1]);
                connectionPool.add(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //取连接
    public static Connection getConnection(){
        Connection connection = connectionPool.get(0);
        connectionPool.remove(0);
        return connection;
    }

    //释放连接
    public static void releaseConnection(Connection connection){
        connectionPool.add(connection);
    }
}
