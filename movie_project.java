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
	   String current_time = "2021-01-01";
		int member_id;
	   
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
			
			/*----------------------------관리자기능-------------------------*/
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
			/*----------------------------관리자기능-------------------------*/
			
			
			/*----------------------------회원기능-------------------------*/
			JButton movie_table = new JButton("영화 조회");
			JButton reservation_check = new JButton("예매 확인");
			JButton exit = new JButton("나가기");
			JLabel lb_member_id = new JLabel();
			c.add(movie_table);
			c.add(reservation_check);
			c.add(exit);
			c.add(lb_member_id);
			movie_table.setVisible(false);
			reservation_check.setVisible(false);
			exit.setVisible(false);
			lb_member_id.setVisible(false);
			
			btn_member.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btn_manager.setVisible(false);
					btn_member.setVisible(false);
					btn_mainPage.setVisible(false);
					JLabel info = new JLabel("회원아이디를 입력하세요!");
					JTextField tx_member_id = new JTextField(5);
					JButton btn_member_id = new JButton("submit");
					c.add(info);
					c.add(tx_member_id);
					c.add(btn_member_id);
					
					btn_member_id.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							member_id = Integer.parseInt(tx_member_id.getText());
							lb_member_id.setText("회원 아이디 : " + Integer.toString(member_id));
							lb_member_id.setVisible(true);
							info.setVisible(false);
							tx_member_id.setVisible(false);
							btn_member_id.setVisible(false);
							c.remove(info);
							c.remove(tx_member_id);
							c.remove(btn_member_id);
							
							movie_table.setVisible(true);
							reservation_check.setVisible(true);
							exit.setVisible(true);
						}
					});
				}
			});
			
			movie_table.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					movie_table.setVisible(false);
					reservation_check.setVisible(false);
					exit.setVisible(false);
					
					JLabel lb_movie = new JLabel("순서대로 : 영화명, 감독명, 배우명, 장르를 입력하세요.");
					JTextField tf_movie_name = new JTextField(50);
					JTextField tf_movie_director = new JTextField(20);
					JTextField tf_movie_actor = new JTextField(20);
					JTextField tf_movie_genre = new JTextField(50);
					JButton submit = new JButton("submit");
					JButton cancle = new JButton("cancle");
					JLabel lb_movie_selected = new JLabel();
					c.add(lb_movie);
					c.add(tf_movie_name);
					c.add(tf_movie_director);
					c.add(tf_movie_actor);
					c.add(tf_movie_genre);
					c.add(submit);
					c.add(cancle);
					c.add(lb_movie_selected);
					lb_movie_selected.setVisible(false);
					
					JTextField tf_reservation_movie_id = new JTextField(5); //예약할 영화 번호 입력하는 text field
					JButton btn_reservation_movie_id = new JButton("text field에 예매하고 싶은 movie_id를 입력한 후 버튼을 누르세요.");
					c.add(tf_reservation_movie_id);
					c.add(btn_reservation_movie_id);
					tf_reservation_movie_id.setVisible(false);
					btn_reservation_movie_id.setVisible(false);
					
					submit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String movie_name = tf_movie_name.getText();
							String movie_director = tf_movie_director.getText();
							String movie_actor = tf_movie_actor.getText();
							String movie_genre = tf_movie_genre.getText();
							
							String query = "select * from movie";
							if(movie_name.length() != 0 || movie_director.length() != 0 || movie_actor.length() != 0 || movie_genre.length() != 0) {
								query += " where ";
							}
							if(movie_name.length() != 0) {
								query += "movie_name = \"" + movie_name + "\"";
								if(movie_director.length() != 0 || movie_actor.length() != 0 || movie_genre.length() != 0) {
									query += " and ";
								}
							}
							if(movie_director.length() != 0) {
								query += "movie_director_name = \"" + movie_director + "\"";
								if(movie_actor.length() != 0 || movie_genre.length() != 0) {
									query += " and ";
								}
							}
							if(movie_actor.length() != 0) {
								query += "movie_actor_name = \"" + movie_actor + "\"";
								if(movie_genre.length() != 0) {
									query += " and ";
								}
							}
							if(movie_genre.length() != 0) {
								query += "movie_genre = \"" + movie_genre + "\"";
							}
							
							lb_movie_selected.setText(member_movie_check(query));
							lb_movie_selected.setVisible(true);
							
							lb_movie.setVisible(false);
							tf_movie_name.setVisible(false);
							tf_movie_director.setVisible(false);
							tf_movie_actor.setVisible(false);
							tf_movie_genre.setVisible(false);
							submit.setVisible(false);
							cancle.setVisible(false);
							c.remove(lb_movie);
							c.remove(tf_movie_name);
							c.remove(tf_movie_director);
							c.remove(tf_movie_actor);
							c.remove(tf_movie_genre);
							c.remove(submit);
							
							tf_reservation_movie_id.setVisible(true);
							btn_reservation_movie_id.setVisible(true);
						}
					});
					
					cancle.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							tf_movie_name.setText("");
							tf_movie_director.setText("");
							tf_movie_actor.setText("");
							tf_movie_genre.setText("");
						}
					});
					
					btn_reservation_movie_id.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JLabel reservation_result = new JLabel(reservation_movie(tf_reservation_movie_id.getText()));
							lb_movie_selected.setText("");
							lb_movie_selected.setVisible(false);
							btn_reservation_movie_id.setVisible(false);
							c.add(reservation_result);
							reservation_result.setVisible(true);
						}
					});
				}
			});
			
			reservation_check.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JLabel reservation_table_info = new JLabel("- 예매번호 - 결제방법 - 결제상태 - 결제금액 - 회원아이디 - 결제일자 -");
					JLabel reserved_movie = new JLabel(reserved_movie_check());
					JButton btn_remove = new JButton("예매삭제하기");
					JButton btn_change_movie = new JButton("다른영화로 바꾸기");
					JButton btn_change_schedule = new JButton("상영일정 변경하기");
					
					btn_remove.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							btn_remove.setVisible(false);
							btn_change_movie.setVisible(false);
							btn_change_schedule.setVisible(false);
							
							JTextField tf_remove = new JTextField(5);
							JButton btn_remove = new JButton("삭제할 reservation_id를 적고 버튼을 누르세요.");
							
							btn_remove.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									String reservation_id = tf_remove.getText();
									JLabel lb_delete = new JLabel(reservation_delete(reservation_id));
									c.add(lb_delete);
									lb_delete.setVisible(false);
									lb_delete.setVisible(true);
								}
							});
							
							c.add(tf_remove);
							c.add(btn_remove);
						}
					});
					
					reservation_check.setVisible(false);
					reservation_table_info.setVisible(false);
					reserved_movie.setVisible(false);
					c.add(reserved_movie);
					c.add(reservation_table_info);
					c.add(btn_remove);
					c.add(btn_change_movie);
					c.add(btn_change_schedule);
					reservation_table_info.setVisible(true);
					reserved_movie.setVisible(true);
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
	   
	   private String member_movie_check(String query) {
		   try {
			   Statement stmt = con.createStatement();
			   ResultSet rs = stmt.executeQuery(query);
			   String relation = "<html><body style='text-align:center;'>";
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
				relation += "</body></html>";
				return relation;
		   } catch(SQLException e) {
			   e.printStackTrace();
		   }
		   return null;
	   }
	   
	   private String reserved_movie_check() {
		   String query = "select * from reservation where member_id = " + Integer.toString(member_id);
		   try {
			   Statement stmt = con.createStatement();
			   ResultSet rs = stmt.executeQuery(query);
			   String relation = "<html><body style='text-align:center;'>";
				while(rs.next()) {
					relation += rs.getInt(1);
					relation += " / "+rs.getString(2);
					relation += " / "+rs.getInt(3);
					relation += " / "+rs.getInt(4);
					relation += " / "+rs.getInt(5);
					relation += " / "+rs.getDate(6)+"<br>";
				  }
				relation += "</body></html>";
				return relation;
		   } catch(SQLException e) {
			   e.printStackTrace();
		   }
		   return null;
	   }
	   
	   private String reservation_movie(String movie_id) {
		  	  try {
		  		 String query_ticket_check = "select count(*) as count from (select * from ticket where schedule_id in (select schedule_id from schedule where movie_id = " + movie_id + ")) t_1 where t_1.reservation_id is null;";
		  	  	 Statement stmt = con.createStatement();
		  	  	 ResultSet rs_ticket_check = stmt.executeQuery(query_ticket_check);
		  	  	 int remain_ticket = 0;
		  	  	 while(rs_ticket_check.next()) {
		  	  		 remain_ticket = rs_ticket_check.getInt(1);
		  	  	 }
		  		 
		  	  	 if(remain_ticket == 0) {
		  	  		 return "매진입니다.";
		  	  	 }
		  	  	 
		  		 
			  	 String query_insert= "INSERT INTO reservation VALUES(?, ?, ?, ?, ?, ?);";
		  	  	 PreparedStatement pstmt = null;
		  	  	 
		  	  	 pstmt = con.prepareStatement(query_insert);
		  	  	 pstmt.setString(1, null);
		  	  	 pstmt.setString(2, "card");
		  	  	 pstmt.setInt(3, 0);
		  	  	 pstmt.setInt(4, 18000);
		  	  	 pstmt.setInt(5, member_id);
		  	  	 pstmt.setDate(6, java.sql.Date.valueOf(current_time));
		  	  	 
		  	  	 pstmt.executeUpdate();
		  	  	 
		  	  	 String query_reservation_key = "SELECT MAX(reservation_id) as reservation_id FROM reservation;";
		  	  	 ResultSet rs_reservation_key = stmt.executeQuery(query_reservation_key);
		  	  	 int reservation_key = 0;
		  	  	 while(rs_reservation_key.next()) {
						reservation_key = rs_reservation_key.getInt(1);
		  	  	 }
		  	  	 
		  	  	 String query_ticket_id = "select ticket_id from (select * from ticket where schedule_id in (select schedule_id from schedule where movie_id = " + movie_id + ")) t_1 where t_1.reservation_id is null limit 1;";
		  	  	 ResultSet rs_ticket_id = stmt.executeQuery(query_ticket_id);
		  	  	 int ticket_id = 0;
		  	  	 while(rs_ticket_id.next()) {
		  	  		 ticket_id = rs_ticket_id.getInt(1);
		  	  	 }
		  	  	 
		  	  	 
			  	 String query_update_ticket= "update ticket set reservation_id = ? where ticket_id = ?;";
		  	  	 pstmt = null;
		  	  	 
		  	  	 pstmt = con.prepareStatement(query_update_ticket);
		  	  	 pstmt.setInt(1, reservation_key);
		  	  	 pstmt.setInt(2, ticket_id);
		  	  	 
		  	  	 pstmt.executeUpdate();
		  	  	 
			  	 String query_update_seat= "update seat set seat_availability = 1 where seat_id = (select seat_id from ticket where ticket_id = ?);";
		  	  	 pstmt = null;
		  	  	 
		  	  	 pstmt = con.prepareStatement(query_update_seat);
		  	  	 pstmt.setInt(1, ticket_id);
		  	  	 
		  	  	 pstmt.executeUpdate();
		  	  	 
		  	  	 return "성공";
		  	  } catch(SQLException e) {
				   e.printStackTrace();
		  	   }
		  	  return null;
	   }
	   
	   private String reservation_delete(String reservation_id) {
		   try {
			  int ticket_id = 0; 
		  	  Statement stmt = con.createStatement();
		  	  PreparedStatement pstmt = null;
		  	  
		  	  String query_ticket_id = "select ticket_id from ticket where reservation_id = " + reservation_id + ";";
		  	  ResultSet rs_ticket_check = stmt.executeQuery(query_ticket_id);
		  	  while(rs_ticket_check.next()) {
		  	  	ticket_id = rs_ticket_check.getInt(1);
		  	  }
		  	  
			  	 String query_update_seat= "update seat set seat_availability = 0 where seat_id = (select seat_id from ticket where ticket_id = ?);";
		  	  	 pstmt = null;
		  	  	 
		  	  	 pstmt = con.prepareStatement(query_update_seat);
		  	  	 pstmt.setInt(1, ticket_id);
		  	  	 
		  	  	 pstmt.executeUpdate();
		  	  
		  	  	 
			  	 String query_update_ticket= "update ticket set reservation_id = ? where ticket_id = ?;";
		  	  	 pstmt = null;
		  	  	 
		  	  	 pstmt = con.prepareStatement(query_update_ticket);
		  	  	 pstmt.setString(1, null);
		  	  	 pstmt.setInt(2, ticket_id);
		  	  	 
		  	  	 pstmt.executeUpdate();
		  	  	 
		  	  	 
		  	  	 stmt.execute("set FOREIGN_KEY_CHECKS = 0;");
			  	 String query_delete_reservation= "delete from reservation where reservation_id = ?;";
		  	  	 pstmt = null;
		  	  	 
		  	  	 pstmt = con.prepareStatement(query_delete_reservation);
		  	  	 pstmt.setInt(1, Integer.parseInt(reservation_id));
		  	  	 
		  	  	 pstmt.executeUpdate();
		  	  	 stmt.execute("set FOREIGN_KEY_CHECKS = 1;");
			   return "삭제성공";
		   } catch(SQLException e) {
			   e.printStackTrace();
	  	   }
	  	  return null;
	   }
	   
	   public static void main (String args[]) {
		   movie_project so = new movie_project();
	   }
}
