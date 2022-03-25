import java.sql.Statement;
import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
public class practice {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/develop";
        String user="root";
        String password="12Dec2000@";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(url, user, password);
            Statement st=con.createStatement();
        st.execute("CREATE TABLE airtravel2(month varchar(10),year1958 int,year1959 int,year1960 int);");
       String sql = "INSERT INTO airtravel2(month,year1958,year1959,year1960) VALUES ( ?, ?, ?, ?)";
       PreparedStatement statement = con.prepareStatement(sql);                                               
       String file = "airtravel.csv";
       String line;
       BufferedReader br =new BufferedReader(new FileReader(file));
       br.readLine();
          while((line = br.readLine()) != null){
                  String[] key=line.split(",");
                  statement.setString(1, key[0]);
                  statement.setInt(2, Integer.parseInt(key[1].trim()));
                  statement.setInt(3, Integer.parseInt(key[2].trim()));
                  statement.setInt(4, Integer.parseInt(key[3].trim()));
                  statement.addBatch();
          }
       statement.executeBatch();
            ResultSet rs=st.executeQuery("Select * from airtravel2");
            System.out.println("Month"+" 1958"+" 1959"+" 1960");
            while(rs.next()){
                System.out.println(rs.getString(1)+" "+rs.getInt(2)+" "+rs.getInt(3)+" "+rs.getInt(4));
            }                         
            con.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}