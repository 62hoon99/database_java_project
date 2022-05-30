package movie_project;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class movie_project extends JFrame{
	   Connection con;
	   
	   public movie_project() {
			setTitle("18011828/유기훈");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container c = getContentPane();
			c.setLayout(new FlowLayout());
			sqlReady();
			
			JButton btn_mainPage = new JButton("첫 화면으로 돌아가기");
			JButton btn_manager = new JButton("관리자");
			JButton btn_member = new JButton("회원");
			JButton btn_manager_1 = new JButton("입력/삭제/변경");
			JButton btn_manager_2 = new JButton("전체 테이블 보기");
			
			btn_mainPage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btn_manager_1.setVisible(false);
					btn_manager_2.setVisible(false);
					btn_manager.setVisible(true);
					btn_member.setVisible(true);
				}
			});
			
			btn_manager.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btn_manager.setVisible(false);
					btn_member.setVisible(false);
					btn_manager_1.setVisible(true);
					btn_manager_2.setVisible(true);
				}
			});
			
			btn_manager_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btn_mainPage.setVisible(false);
					btn_manager_1.setVisible(false);
					btn_manager_2.setVisible(false);
					
					JButton exit = new JButton("나가기");
					JLabel text_label = new JLabel("");
				
					
					JTextField manager_query = new JTextField(50);
					JButton btn_submit = new JButton("submit");
					c.add(manager_query);
					c.add(btn_submit);
					
					btn_submit.addActionListener(new ActionListener() { // 관리자1
						public void actionPerformed(ActionEvent e) {
							String query = manager_query.getText();
							String res = manager_1(query);
							text_label.setText(res);						
						}
					});
					exit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							btn_manager_1.setVisible(true);
							btn_manager_2.setVisible(true);
							btn_mainPage.setVisible(true);
							manager_query.setVisible(false);
							btn_submit.setVisible(false);
							exit.setVisible(false);
							text_label.setVisible(false);
							c.remove(manager_query);
							c.remove(btn_submit);
							c.remove(exit);
							c.remove(text_label);
						}
					});
					
					c.add(exit);
					c.add(text_label);
				}
			});
			
			btn_manager_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btn_manager_1.setVisible(false);
					btn_manager_2.setVisible(false);
					btn_mainPage.setVisible(false);
					
					JButton exit = new JButton("나가기");
					JLabel text_label = new JLabel("");
					
					String table_name[] = new String[7];
					table_name[0] = new String("movie");
					table_name[1] = new String("schedule");
					table_name[2] = new String("ticket");
					table_name[3] = new String("theater");
					table_name[4] = new String("seat");
					table_name[5] = new String("reservation");
					table_name[6] = new String("member");
					
					JButton tables[] = new JButton[7];

					for(int i = 0; i < 7; i++) {
						tables[i] = new JButton(table_name[i]);
						c.add(tables[i]);
					}
					
					tables[0].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String relation = manager_2(table_name[0]);
							text_label.setText(relation);
						}
					});
					tables[1].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String relation = manager_2(table_name[1]);
							text_label.setText(relation);
						}
					});
					tables[2].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String relation = manager_2(table_name[2]);
							text_label.setText(relation);
						}
					});
					tables[3].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String relation = manager_2(table_name[3]);
							text_label.setText(relation);
						}
					});
					tables[4].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String relation = manager_2(table_name[4]);
							text_label.setText(relation);
						}
					});
					tables[5].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String relation = manager_2(table_name[5]);
							text_label.setText(relation);
						}
					});
					tables[6].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String relation = manager_2(table_name[6]);
							text_label.setText(relation);
						}
					});
					exit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							for(int i = 0; i < 7; i++) {
								tables[i].setVisible(false);
								c.remove(tables[i]);
							}
							btn_manager_1.setVisible(true);
							btn_manager_2.setVisible(true);
							btn_mainPage.setVisible(true);
							exit.setVisible(false);
							text_label.setVisible(false);
							c.remove(exit);
							c.remove(text_label);
						}
					});
					c.add(exit);
					c.add(text_label);
				}
			});
			
			c.add(btn_mainPage);
			c.add(btn_manager_1);
			c.add(btn_manager_2);
			c.add(btn_manager);
			c.add(btn_member);
			btn_manager_1.setVisible(false);
			btn_manager_2.setVisible(false);
			setSize(700, 700);
			setVisible(true);
	   }
	   
	   public void sqlReady() {
		     String 
		     url="jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul"; 
		     String userid="madang";
		     String pwd="madang"; 
		     try { /* 드라이버를 찾는 과정 */
		       Class.forName("com.mysql.cj.jdbc.Driver");   
		       System.out.println("드라이버 로드 성공");
		     } catch(ClassNotFoundException e) {
		         e.printStackTrace();
		      }    
		     try { /* 데이터베이스를 연결하는 과정 */
		       System.out.println("데이터베이스 연결 준비...");	
		       con=DriverManager.getConnection(url, userid, pwd);
		       System.out.println("데이터베이스 연결 성공");
		     } catch(SQLException e) {
		         e.printStackTrace();
		       }
		  	}
	   
	   private String manager_1(String query) {
		  	  try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
				   Statement stmt = con.createStatement();
				   stmt.execute(query);
	
			  	  } catch(SQLException e) {
			          return e.getMessage();
			  	   } catch(Exception e) {
			  		   return e.toString();
			  	   }
			  	  return "성공";
	   }
	   
	   private String manager_2(String table_name) {
		   String query= "select * from " + table_name;
		   try {
			   Statement stmt = con.createStatement();
			   ResultSet rs = stmt.executeQuery(query);
			   String relation = "<html><body style='text-align:center;'>";
			   if(table_name.equals("movie")) {
					while(rs.next()) {
						relation += rs.getInt(1);
						relation += " / "+rs.getString(2);
						relation += " / "+rs.getTime(3);
						relation += " / "+rs.getString(4);
						relation += " / "+rs.getString(5);
						relation += " / "+rs.getString(6);
						relation += " / "+rs.getString(7);
						relation += " / "+rs.getString(8);
						relation += " / "+rs.getDate(9)+"<br>";
					  }
			   }
			   else if(table_name.equals("schedule")) {
					while(rs.next()) {
						relation += rs.getInt(1);
						relation += " / "+rs.getInt(2);
						relation += " / "+rs.getInt(3);
						relation += " / "+rs.getDate(4);
						relation += " / "+rs.getString(5);
						relation += " / "+rs.getInt(6);
						relation += " / "+rs.getTime(7)+"<br>";
					  }
			   }
			   else if(table_name.equals("ticket")) {
					while(rs.next()) {
						relation += rs.getInt(1);
						relation += " / "+rs.getInt(2);
						relation += " / "+rs.getInt(3);
						relation += " / "+rs.getInt(4);
						relation += " / "+rs.getInt(5);
						relation += " / "+rs.getInt(6);
						relation += " / "+rs.getInt(7);
						relation += " / "+rs.getInt(8)+"<br>";
					  }
			   }
			   else if(table_name.equals("theater")) {
					while(rs.next()) {
						relation += rs.getInt(1);
						relation += " / "+rs.getInt(2);
						relation += " / "+rs.getInt(3)+"<br>";
					  }
			   }
			   else if(table_name.equals("seat")) {
					while(rs.next()) {
						relation += rs.getInt(1);
						relation += " / "+rs.getInt(2);
						relation += " / "+rs.getInt(3)+"<br>";
					  }
			   }
			   else if(table_name.equals("reservation")) {
					while(rs.next()) {
						relation += rs.getInt(1);
						relation += " / "+rs.getString(2);
						relation += " / "+rs.getInt(3);
						relation += " / "+rs.getInt(4);
						relation += " / "+rs.getInt(5);
						relation += " / "+rs.getDate(6)+"<br>";
					  }
			   }
			   else if(table_name.equals("member")) {
					while(rs.next()) {
						relation += rs.getInt(1);
						relation += " / "+rs.getString(2);
						relation += " / "+rs.getString(3);
						relation += " / "+rs.getString(4)+"<br>";
					  }
			   }
			   
				relation += "</body></html>";
				return relation;
		   } catch(SQLException e) {
			   e.printStackTrace();
		   }
		   return null;
	   }
	   
	   public static void main (String args[]) {
		   movie_project so = new movie_project();
	   }
}
