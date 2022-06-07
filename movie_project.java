package movie_project;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
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
			JButton btn_manager_1 = new JButton("입력");
			JButton btn_manager_3 = new JButton("삭제");
			JButton btn_manager_4 = new JButton("변경");
			JButton btn_manager_2 = new JButton("전체 테이블 보기");
			JButton btn_manager_5 = new JButton("초기화");
			
			btn_mainPage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btn_manager_1.setVisible(false);
					btn_manager_2.setVisible(false);
					btn_manager_3.setVisible(false);
					btn_manager_4.setVisible(false);
					btn_manager_5.setVisible(false);
					btn_manager.setVisible(true);
					btn_member.setVisible(true);
				}
			});
			
			btn_manager.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btn_manager.setVisible(false);
					btn_member.setVisible(false);
					btn_manager_1.setVisible(true);
					btn_manager_3.setVisible(true);
					btn_manager_4.setVisible(true);
					btn_manager_2.setVisible(true);
					btn_manager_5.setVisible(true);
				}
			});
			
			/*----------------------------관리자기능-------------------------*/
			btn_manager_1.addActionListener(new ActionListener() { //입력
				public void actionPerformed(ActionEvent e) {
					btn_mainPage.setVisible(false);
					btn_manager_1.setVisible(false);
					btn_manager_3.setVisible(false);
					btn_manager_4.setVisible(false);
					btn_manager_2.setVisible(false);
					btn_manager_5.setVisible(false);
					
					JButton exit = new JButton("나가기");
					JLabel text_label = new JLabel("");
				
					
					JTextField manager_query = new JTextField(50);
					JButton btn_submit = new JButton("입력_submit");
					c.add(manager_query);
					c.add(btn_submit);
					
					btn_submit.addActionListener(new ActionListener() { // 관리자1
						public void actionPerformed(ActionEvent e) {
							String query = manager_query.getText();
							String res = manager_1(query);
							text_label.setVisible(false);
							text_label.setText(res);
							text_label.setVisible(true);
						}
					});
					exit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							btn_manager_1.setVisible(true);
							btn_manager_3.setVisible(true);
							btn_manager_4.setVisible(true);
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
			
			btn_manager_3.addActionListener(new ActionListener() { //삭제
				public void actionPerformed(ActionEvent e) {
					btn_mainPage.setVisible(false);
					btn_manager_1.setVisible(false);
					btn_manager_3.setVisible(false);
					btn_manager_4.setVisible(false);
					btn_manager_2.setVisible(false);
					btn_manager_5.setVisible(false);
					
					JButton exit = new JButton("나가기");
					JLabel text_label = new JLabel();
				
					
					JTextField manager_query_table = new JTextField(10);
					JTextField manager_query_condition = new JTextField(50);
					manager_query_table.setText("여기는 table명");
					manager_query_condition.setText("여기는 조건절");
					JButton btn_submit = new JButton("삭제_submit");
					c.add(manager_query_table);
					c.add(manager_query_condition);
					c.add(btn_submit);
					
					btn_submit.addActionListener(new ActionListener() { // 관리자1
						public void actionPerformed(ActionEvent e) {
							String query_table = manager_query_table.getText();
							String query_condition = manager_query_condition.getText();
							String res = manager_3(query_table, query_condition);
							text_label.setVisible(false);
							text_label.setText(res);
							text_label.setVisible(true);
						}
					});
					exit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							btn_manager_1.setVisible(true);
							btn_manager_3.setVisible(true);
							btn_manager_4.setVisible(true);
							btn_manager_2.setVisible(true);
							btn_manager_5.setVisible(true);
							btn_mainPage.setVisible(true);
							manager_query_table.setVisible(false);
							manager_query_condition.setVisible(false);
							btn_submit.setVisible(false);
							exit.setVisible(false);
							text_label.setVisible(false);
							c.remove(manager_query_table);
							c.remove(manager_query_condition);
							c.remove(btn_submit);
							c.remove(exit);
							c.remove(text_label);
						}
					});
					
					c.add(exit);
					c.add(text_label);
				}
			});
			
			btn_manager_5.addActionListener(new ActionListener() { // 관리자1
				public void actionPerformed(ActionEvent e) {
					btn_mainPage.setVisible(false);
					btn_manager_1.setVisible(false);
					btn_manager_3.setVisible(false);
					btn_manager_4.setVisible(false);
					btn_manager_2.setVisible(false);
					btn_manager_5.setVisible(false);
					
					JButton exit = new JButton("나가기");
					JLabel text_label = new JLabel(reset());
					
					exit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							btn_manager_1.setVisible(true);
							btn_manager_3.setVisible(true);
							btn_manager_4.setVisible(true);
							btn_manager_2.setVisible(true);
							btn_manager_5.setVisible(true);
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
			
			btn_manager_4.addActionListener(new ActionListener() { //변경
				public void actionPerformed(ActionEvent e) {
					btn_mainPage.setVisible(false);
					btn_manager_1.setVisible(false);
					btn_manager_3.setVisible(false);
					btn_manager_4.setVisible(false);
					btn_manager_2.setVisible(false);
					btn_manager_5.setVisible(false);
					
					JButton exit = new JButton("나가기");
					JLabel text_label = new JLabel();
				
					
					JTextField manager_query_table = new JTextField(10);
					JTextField manager_query_set = new JTextField(50);
					JTextField manager_query_condition = new JTextField(50);
					manager_query_table.setText("여기는 table명");
					manager_query_set.setText("여기는 바꾸는 내용 (set 다음에 올 내용)");
					manager_query_condition.setText("여기는 조건절");
					JButton btn_submit = new JButton("변경_submit");
					c.add(manager_query_table);
					c.add(manager_query_set);
					c.add(manager_query_condition);
					c.add(btn_submit);
					
					btn_submit.addActionListener(new ActionListener() { // 관리자1
						public void actionPerformed(ActionEvent e) {
							String query_table = manager_query_table.getText();
							String query_set = manager_query_set.getText();
							String query_condition = manager_query_condition.getText();
							String res = manager_4(query_table, query_condition, query_set);
							text_label.setVisible(false);
							text_label.setText(res);
							text_label.setVisible(true);
						}
					});
					exit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							btn_manager_1.setVisible(true);
							btn_manager_3.setVisible(true);
							btn_manager_4.setVisible(true);
							btn_manager_2.setVisible(true);
							btn_manager_5.setVisible(true);
							btn_mainPage.setVisible(true);
							manager_query_table.setVisible(false);
							manager_query_set.setVisible(false);
							manager_query_condition.setVisible(false);
							btn_submit.setVisible(false);
							exit.setVisible(false);
							text_label.setVisible(false);
							c.remove(manager_query_table);
							c.remove(manager_query_set);
							c.remove(manager_query_condition);
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
					btn_manager_3.setVisible(false);
					btn_manager_4.setVisible(false);
					btn_manager_2.setVisible(false);
					btn_mainPage.setVisible(false);
					btn_manager_5.setVisible(false);
					
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
							btn_manager_3.setVisible(true);
							btn_manager_4.setVisible(true);
							btn_manager_2.setVisible(true);
							btn_manager_5.setVisible(true);
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
			JLabel lb_member_id = new JLabel();
			c.add(movie_table);
			c.add(reservation_check);
			c.add(lb_member_id);
			movie_table.setVisible(false);
			reservation_check.setVisible(false);
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
						}
					});
				}
			});
			
			movie_table.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					movie_table.setVisible(false);
					reservation_check.setVisible(false);
					
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
					JLabel reserved_movie = new JLabel(reserved_movie_check());
					reserved_movie.setVisible(false);
					movie_table.setVisible(false);
					JButton btn_detail = new JButton("예매정보 확인하기");
					JButton btn_remove = new JButton("예매 삭제하기");
					JButton btn_change_movie = new JButton("다른영화로 바꾸기");
					JButton btn_change_schedule = new JButton("상영일정 변경하기");
					
					btn_detail.addActionListener(new ActionListener() { //예매정보 확인하기
						public void actionPerformed(ActionEvent e) {
							btn_detail.setVisible(false);
							btn_remove.setVisible(false);
							btn_change_movie.setVisible(false);
							btn_change_schedule.setVisible(false);
							
							JLabel lb_reservation_detail = new JLabel();
							
							lb_reservation_detail.setText(reservation_detail());
							lb_reservation_detail.setVisible(false);
							lb_reservation_detail.setVisible(true);
							c.add(lb_reservation_detail);
							c.add(btn_remove);
						}
					});
					
					btn_remove.addActionListener(new ActionListener() { //예매삭제
						public void actionPerformed(ActionEvent e) {
							reserved_movie.setVisible(true);
							btn_detail.setVisible(false);
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
					
					btn_change_movie.addActionListener(new ActionListener() { //다른영화로 바꾸기
						public void actionPerformed(ActionEvent e) {
							reserved_movie.setVisible(true);
							btn_detail.setVisible(false);
							btn_remove.setVisible(false);
							btn_change_movie.setVisible(false);
							btn_change_schedule.setVisible(false);
							
							JTextField tf_reservation_id = new JTextField(30);
							tf_reservation_id.setText("변경하고 싶은 reservation_id를 입력하세요.");
							JTextField tf_movie_id = new JTextField(30);
							tf_movie_id.setText("예약하고 싶은 movie_id를 입력하세요.");
							JButton btn_remove = new JButton("submit");
							
							btn_remove.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									String reservation_id = tf_reservation_id.getText();
									String movie_id = tf_movie_id.getText();
									JLabel lb_result = new JLabel();
									if(reservation_movie(movie_id).equals("성공")) {
										reservation_delete(reservation_id);
										lb_result.setText("성공");
									}
									else {
										lb_result.setText("매진입니다.");
									}
									c.add(lb_result);
									lb_result.setVisible(false);
									lb_result.setVisible(true);
								}
							});
							
							c.add(tf_reservation_id);
							c.add(tf_movie_id);
							c.add(btn_remove);
						}
					});
					
					btn_change_schedule.addActionListener(new ActionListener() { //상영일정 변경하기
						public void actionPerformed(ActionEvent e) {
							reserved_movie.setVisible(true);
							btn_detail.setVisible(false);
							btn_remove.setVisible(false);
							btn_change_movie.setVisible(false);
							btn_change_schedule.setVisible(false);
							JLabel lb_schedule_check = new JLabel(reservation_schedule_check());
							
							JTextField tf_reservation_id = new JTextField(30);
							JTextField tf_schedule_id = new JTextField(30);
							tf_reservation_id.setText("변경할 reservation_id를 이곳에 입력하세요.");
							tf_schedule_id.setText("변경을 원하는 schedule_id를 이곳에 입력하세요.");
							JButton btn_remove = new JButton("submit");
							
							btn_remove.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									String reservation_id = tf_reservation_id.getText();
									String schedule_id = tf_schedule_id.getText();
									JLabel lb_result = new JLabel(reservation_change_schedule(schedule_id, reservation_id));
									c.add(lb_result);
									lb_result.setVisible(false);
									lb_result.setVisible(true);
								}
							});
							
							c.add(lb_schedule_check);
							c.add(tf_reservation_id);
							c.add(tf_schedule_id);
							c.add(btn_remove);
						}
					});
					
					reservation_check.setVisible(false);
					c.add(reserved_movie);
					c.add(btn_detail);
					c.add(btn_remove);
					c.add(btn_change_movie);
					c.add(btn_change_schedule);
//					reservation_table_info.setVisible(true);
//					reserved_movie.setVisible(true);
				}
			});

			
			c.add(btn_mainPage);
			c.add(btn_manager_1);
			c.add(btn_manager_3);
			c.add(btn_manager_4);
			c.add(btn_manager_2);
			c.add(btn_manager_5);
			c.add(btn_manager);
			c.add(btn_member);
			btn_manager_1.setVisible(false);
			btn_manager_3.setVisible(false);
			btn_manager_4.setVisible(false);
			btn_manager_2.setVisible(false);
			btn_manager_5.setVisible(false);
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
	   
	   private String manager_1(String query) { //입력
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
	   
	   private String manager_3(String table, String condition) { //삭제
		  	  try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
		  		  String query = "delete from " + table + " where " + condition + ";";
		  		  
				   Statement stmt = con.createStatement();
				   
			  	   stmt.execute("set FOREIGN_KEY_CHECKS = 0;");
				   stmt.execute(query);
			  	   stmt.execute("set FOREIGN_KEY_CHECKS = 1;");
	
			  	  } catch(SQLException e) {
			          return e.getMessage();
			  	   } catch(Exception e) {
			  		   return e.toString();
			  	   }
			  	  return "성공";
	   }
	   
	   private String manager_4(String table, String condition, String set) { //변경
		  	  try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
		  		  String query = "update " + table + " set " + set + " where " + condition + ";";
				   Statement stmt = con.createStatement();
				   
			  	   stmt.execute("set FOREIGN_KEY_CHECKS = 0;");
				   stmt.execute(query);
			  	   stmt.execute("set FOREIGN_KEY_CHECKS = 1;");
	
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
		      return e.getMessage();
		   }
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
			      return e.getMessage();
		   }
	   }
	   
	   private String reserved_movie_check() {
		   String query = "select movie.movie_name, t_2.reservation_id from (select schedule.movie_id, t_1.reservation_id from (select schedule_id, reservation_id from ticket where reservation_id in (select reservation_id from reservation where member_id = " + member_id +")) t_1, schedule where t_1.schedule_id = schedule.schedule_id) t_2, movie where t_2.movie_id = movie.movie_id;";
		   try {
			   Statement stmt = con.createStatement();
			   ResultSet rs = stmt.executeQuery(query);
			   String relation = "<html><body style='text-align:center;'>";
				while(rs.next()) {
					relation += "movie_name : " + rs.getString(1);
					relation += " / " + "reservation_id : " + rs.getInt(2)+"<br>";
				  }
				relation += "</body></html>";
				return relation;
		   } catch(SQLException e) {
			      return e.getMessage();
		   }
	   }
	   
	   private String reservation_schedule_check() {
		   String query = "select movie.movie_name, schedule.schedule_screening_day, schedule.schedule_start_time, schedule.schedule_id from (select schedule.movie_id, t_1.reservation_id from (select schedule_id, reservation_id from ticket where reservation_id in (select reservation_id from reservation where member_id = " + member_id + ")) t_1, schedule where t_1.schedule_id = schedule.schedule_id) t_2, movie, schedule where t_2.movie_id = movie.movie_id and t_2.movie_id = schedule.movie_id;";
		   try {
			   Statement stmt = con.createStatement();
			   ResultSet rs = stmt.executeQuery(query);
			   String relation = "<html><body style='text-align:center;'>";
				while(rs.next()) {
					relation += "movie_name : " + rs.getString(1);
					relation += " / " + "day : " + rs.getString(2);
					relation += " / " + "start_time : " + rs.getTime(3);
					relation += " / " + "schedule_id : " + rs.getInt(4)+"<br>";
				  }
				relation += "</body></html>";
				return relation;
		   } catch(SQLException e) {
			      return e.getMessage();
		   }
	   }
	   
	   private String reservation_detail() {
		   String query = "select movie.movie_name, t_1.schedule_opening_day, t_1.theater_id, t_1.seat_id, t_1.standard_price from (select schedule.schedule_opening_day, schedule.theater_id, schedule.schedule_id, ticket.seat_id, ticket.ticket_id, ticket.standard_price, schedule.movie_id from schedule, ticket where schedule.schedule_id = ticket.schedule_id and ticket.ticket_id in (select ticket_id from ticket where reservation_id in (select reservation_id from reservation where member_id = " + member_id + "))) t_1, movie where t_1.schedule_id in (select schedule_id from ticket where reservation_id in (select reservation_id from reservation where member_id = " + member_id +")) and t_1.movie_id = movie.movie_id;";
		   try {
			   Statement stmt = con.createStatement();
			   ResultSet rs = stmt.executeQuery(query);
			   String relation = "<html><body style='text-align:center;'>";
				while(rs.next()) {
					relation += rs.getString(1);
					relation += " / "+rs.getDate(2);
					relation += " / "+rs.getInt(3);
					relation += " / "+rs.getInt(4);
					relation += " / "+rs.getInt(5) +"<br>";
				  }
				relation += "</body></html>";
				return relation;
		   } catch(SQLException e) {
			      return e.getMessage();
		   }
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
			      return e.getMessage();
		  	   }
	   }
	   
	   private String reservation_change_schedule(String schedule_id, String reservation_id) {
		   try {
			  	 String query_update_ticket= "update ticket set schedule_id = ? where reservation_id = ?;";
			  	  PreparedStatement pstmt = null;
		  	  	 
		  	  	 pstmt = con.prepareStatement(query_update_ticket);
		  	  	 pstmt.setString(1, schedule_id);
		  	  	 pstmt.setString(2, reservation_id);
		  	  	 
		  	  	 pstmt.executeUpdate();
			  	 return "성공"; 
		   }catch(SQLException e) {
			      return e.getMessage();
	  	   }
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
			      return e.getMessage();
	  	   }
	   }
	   
	   private String reset() {
		   String query = "-- MySQL Workbench Forward Engineering\r\n"
		   		
		   		+ "SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;\r\n"
		   		+ "SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;\r\n"
		   		+ "SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';\r\n"
		   		
		   		+ "-- -----------------------------------------------------\r\n"
		   		+ "-- madang 설정 코드를 삽입함\r\n"
		   		+ "-- -----------------------------------------------------\r\n"
		   		+ "DROP DATABASE IF EXISTS  madang;\r\n"
		   		+ "create database madang;\r\n"
		   		+ "grant all privileges on madang.* to madang@localhost with grant option;\r\n"
		   		+ "commit;\r\n"
		   		+ "-- -----------------------------------------------------\r\n"
		   		+ "-- madang 설정 코드를 삽입함\r\n"
		   		+ "-- -----------------------------------------------------\r\n"
		   		+ "USE madang;\r\n"
		   		
		   		+ "-- -----------------------------------------------------\r\n"
		   		+ "-- Table `madang`.`movie`\r\n"
		   		+ "-- -----------------------------------------------------\r\n"
		   		+ "CREATE TABLE `madang`.`movie` (`movie_id` INT NOT NULL,`movie_name` VARCHAR(50) NULL,`movie_showtime` TIME NULL,`movie_rating` VARCHAR(50) NULL,`movie_director_name` VARCHAR(20) NULL,`movie_actor_name` VARCHAR(20) NULL,`movie_genre` VARCHAR(50) NULL,`movie_introduction` VARCHAR(100) NULL,`movie_release_date` DATE NULL,PRIMARY KEY (`movie_id`))"
		   		+ "ENGINE = InnoDB;\r\n"
		   		
		   		
		   		+ "-- -----------------------------------------------------\r\n"
		   		+ "-- Table `madang`.`theater`\r\n"
		   		+ "-- -----------------------------------------------------\r\n"
		   		+ "CREATE TABLE `madang`.`theater` (`theater_id` INT NOT NULL,`seat_capacity` INT NULL,`theater_availability` TINYINT NULL,PRIMARY KEY (`theater_id`))"
		   		+ "ENGINE = InnoDB;\r\n"
		   		
		   		
		   		+ "-- -----------------------------------------------------\r\n"
		   		+ "-- Table `madang`.`schedule`\r\n"
		   		+ "-- -----------------------------------------------------\r\n"
		   		+ "CREATE TABLE `madang`.`schedule` (`schedule_id` INT NOT NULL,`movie_id` INT NOT NULL,`theater_id` INT NOT NULL,`schedule_opening_day` DATE NULL,`schedule_screening_day` VARCHAR(20) NULL,`schedule_number` INT NULL,`schedule_start_time` TIME NULL,PRIMARY KEY (`schedule_id`), INDEX `fk_schedule_movie_idx` (`movie_id` ASC) VISIBLE,INDEX `fk_schedule_theater1_idx` (`theater_id` ASC) VISIBLE,CONSTRAINT `fk_schedule_movie` FOREIGN KEY (`movie_id`)REFERENCES `madang`.`movie` (`movie_id`)ON DELETE NO ACTION ON UPDATE NO ACTION,CONSTRAINT `fk_schedule_theater1` FOREIGN KEY (`theater_id`) REFERENCES `madang`.`theater` (`theater_id`) ON DELETE NO ACTION ON UPDATE NO ACTION)"
		   		+ "ENGINE = InnoDB;\r\n"
		   		
		   		
		   		+ "-- -----------------------------------------------------\r\n"
		   		+ "-- Table `madang`.`seat`\r\n"
		   		+ "-- -----------------------------------------------------\r\n"
		   		+ "CREATE TABLE `madang`.`seat` (`seat_id` INT NOT NULL,`theater_id` INT NOT NULL,`seat_availability` TINYINT NULL,PRIMARY KEY (`seat_id`, `theater_id`),INDEX `fk_seat_theater1_idx` (`theater_id` ASC) VISIBLE,CONSTRAINT `fk_seat_theater1` FOREIGN KEY (`theater_id`) REFERENCES `madang`.`theater` (`theater_id`) ON DELETE NO ACTION ON UPDATE NO ACTION)"
		   		+ "ENGINE = InnoDB;\r\n"
		   		
		   		
		   		+ "-- -----------------------------------------------------\r\n"
		   		+ "-- Table `madang`.`member`\r\n"
		   		+ "-- -----------------------------------------------------\r\n"
		   		+ "CREATE TABLE `madang`.`member` (`member_id` INT NOT NULL,`member_name` VARCHAR(20) NULL,`member_phone_num` VARCHAR(15) NULL,`member_email` VARCHAR(30) NULL,PRIMARY KEY (`member_id`))"
		   		+ "ENGINE = InnoDB;\r\n"
		   		
		   		
		   		+ "-- -----------------------------------------------------\r\n"
		   		+ "-- Table `madang`.`reservation`\r\n"
		   		+ "-- -----------------------------------------------------\r\n"
		   		+ "CREATE TABLE `madang`.`reservation` (`reservation_id` INT auto_increment NOT NULL,`payment_option` VARCHAR(20) NULL,`payment_status` TINYINT NULL,`payment_amount` INT NULL,  `member_id` INT NOT NULL,  `payment_date` DATE NULL,PRIMARY KEY (`reservation_id`),INDEX `fk_reservation_member1_idx` (`member_id` ASC) VISIBLE,CONSTRAINT `fk_reservation_member1`FOREIGN KEY (`member_id`)REFERENCES `madang`.`member` (`member_id`)ON DELETE NO ACTION ON UPDATE NO ACTION)"
		   		+ "ENGINE = InnoDB;\r\n"
		   		
		   		
		   		+ "-- -----------------------------------------------------\r\n"
		   		+ "-- Table `madang`.`ticket`\r\n"
		   		+ "-- -----------------------------------------------------\r\n"
		   		+ "CREATE TABLE `madang`.`ticket` (`ticket_id` INT NOT NULL,`schedule_id` INT NOT NULL,`theater_id` INT NOT NULL,`seat_id` INT NOT NULL,`reservation_id` INT,`issuing_check` TINYINT NULL,`standard_price` INT NULL,`selling_price` INT NULL,PRIMARY KEY (`ticket_id`),INDEX `fk_ticket_schedule1_idx` (`schedule_id` ASC) VISIBLE,INDEX `fk_ticket_theater1_idx` (`theater_id` ASC) VISIBLE,INDEX `fk_ticket_seat1_idx` (`seat_id` ASC) VISIBLE,INDEX `fk_ticket_reservation1_idx` (`reservation_id` ASC) VISIBLE,CONSTRAINT `fk_ticket_schedule1`FOREIGN KEY (`schedule_id`)REFERENCES `madang`.`schedule` (`schedule_id`)ON DELETE NO ACTION ON UPDATE NO ACTION,CONSTRAINT `fk_ticket_theater1` FOREIGN KEY (`theater_id`) REFERENCES `madang`.`theater` (`theater_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,CONSTRAINT `fk_ticket_seat1` FOREIGN KEY (`seat_id`)REFERENCES `madang`.`seat` (`seat_id`)ON DELETE NO ACTION ON UPDATE NO ACTION,CONSTRAINT `fk_ticket_reservation1`FOREIGN KEY (`reservation_id`)REFERENCES `madang`.`reservation` (`reservation_id`)ON DELETE NO ACTION ON UPDATE NO ACTION)"
		   		+ "ENGINE = InnoDB;\r\n"
		   		
		   		+ "INSERT INTO movie VALUES(1,'범죄도시2','1:46','15세 관람가','이상용','마동석','액션','금천서 강력반은 베트남으로 도주한 용의자를 인도받아 오라는 미션을 받는데, 현지 용의자에게서 수상함을 느끼고, 악행을 벌이는 ‘강해상’을 쫓는다','2021-01-18');            \r\n"
		   		+ "INSERT INTO movie VALUES(2,'닥터 스트레인지','2:06','12세 관람가','샘 레이미','베네딕트 컴버배치','판타지','끝없이 균열되는 차원과 뒤엉킨 시공간의 멀티버스가 열리며 오랜 동료들, 그리고 차원을 넘어 들어온 새로운 존재들을 맞닥뜨리게 된 닥터 스트레인지!','2021-02-04');            \r\n"
		   		+ "INSERT INTO movie VALUES(3,'안녕하세요','1:58','12세 관람가','차봉주','김환희','드라마','외로운 세상에서 죽음을 결심한 열아홉 수미. 죽는 법을 알려주겠다는 서진의 제안에 따라 호스피스 병동을 찾아간다. 예상치 못한 유쾌함과 따뜻함이 수미를 반기는데..','2021-03-25');            \r\n"
		   		+ "INSERT INTO movie VALUES(4,'몬스터 싱어','1:30','전체 관람가','비보 버거론','바네사 파라디','애니메이션','파리는 도시에서 목격된 미스터리 괴물로 떠들썩하다. 소문의 주인공은 프랑코 무서운 외모 때문에 쫓기던 그는 가수 루실을 만나 가면을 쓴 가수로 데뷔하게 되는데..','2021-04-26');            \r\n"
		   		+ "INSERT INTO movie VALUES(5,'배드 가이즈','1:40','전체 관람가','피에르 페리펠','샘 록웰','애니메이션','완벽한 팀플레이를 펼치는 최고의 나쁜 녀석들이 실수로 체포된다. 그들도 착해질 수 있다는 박사의 주장으로 나쁜 녀석들은 바른 생활 갓생 프로젝트에 투입되게 되는데...','2021-05-04');            \r\n"
		   		+ "INSERT INTO movie VALUES(6,'오마주','1:48','12세 관람가','신수원','이정은','드라마','사라진 필름을 찾아 홍감독의 마지막 행적을 따라가던 지완은 그 시간 속을 여행하게 되는데... 어쩐지, 희미해진 꿈과 영화에 대한 열정이 되살아나는 것만 같다.','2021-06-26');            \r\n"
		   		+ "INSERT INTO movie VALUES(7,'피는 물보다 진하다','1:30','15세 관람가','김희성','조동혁','액션','폭력조직 백정파는 악명 높은 해결사, 일명 도깨비를 앞세워 일대를 장악한다. 그러나, 베일에 싸인 ‘도깨비’ 두현은 영민 대신 수감생활을 하게 되는데..','2021-07-25');            \r\n"
		   		+ "INSERT INTO movie VALUES(8,'더 노비스','1:37','15세 관람가','해더웨이','이사벨 퍼만','스릴러','대학 신입생 ‘알렉스’는 교내 조정부에 가입한 후 동급생 ‘제이미’에게 경쟁심을 느낀다. 최고를 갈망하는 ‘알렉스’는 팀 1군에 들기 위해 훈련을 거듭하는데··.','2021-08-25');            \r\n"
		   		+ "INSERT INTO movie VALUES(9,'플레이그라운드','1:12','12세 관람가','로라 완델','마야 반데베크','드라마','일곱 살 노라는 낯선 학교가 무섭다. 아벨은 동생 노라를 위로하지만 사실 아벨도 학교가 두렵다. 노라는 우연히 아벨이 친구들에게 괴롭힘 당하는 걸 목격한다.','2021-09-25');            \r\n"
		   		+ "INSERT INTO movie VALUES(10,'히든','1:58','15세 관람가','미카엘 하네케','다니엘 오떼유','드라마','어린 시절, 숨기고 싶었던 기억은 악몽이 되어 그에게 돌아오고 조르쥬는 범인을 직접 찾아 나서게 된다. 범인을 알아낸 순간, 조르쥬는 더욱 끔찍한 진실과 마주하게 되는데..','2021-10-21');   \r\n"
		   		
		   		
		   		+ "INSERT INTO member VALUES(1,'유기훈','010-7283-2849','ygh@gmail.com');\r\n"
		   		+ "INSERT INTO member VALUES(2,'서영선','010-3058-1482','sys@gmail.com');\r\n"
		   		+ "INSERT INTO member VALUES(3,'김민서','010-3738-1339','kms@gmail.com');\r\n"
		   		+ "INSERT INTO member VALUES(4,'강민구','010-4800-9380','kmg@gmail.com');\r\n"
		   		+ "INSERT INTO member VALUES(5,'나유미','010-1038-3842','nym@gmail.com');\r\n"
		   		+ "INSERT INTO member VALUES(6,'도민혁','010-1125-9955','dmh@gmail.com');\r\n"
		   		+ "INSERT INTO member VALUES(7,'문예지','010-2201-7787','myg@gmail.com');\r\n"
		   		+ "INSERT INTO member VALUES(8,'박보민','010-1313-9471','ppm@gmail.com');\r\n"
		   		+ "INSERT INTO member VALUES(9,'사정원','010-1098-2312','sjy@gmail.com');\r\n"
		   		+ "INSERT INTO member VALUES(10,'안세영','010-3829-3662','asy@gmail.com');\r\n"
		   		
		   		+ "INSERT INTO reservation VALUES(100,'card',1,24000,1,'2021-01-26');\r\n"
		   		+ "INSERT INTO reservation VALUES(101,'cash',1,16000,2,'2021-02-27');\r\n"
		   		+ "INSERT INTO reservation VALUES(102,'card',0,14000,3,'2021-03-27');\r\n"
		   		+ "INSERT INTO reservation VALUES(103,'cash',0,22000,4,'2021-04-27');\r\n"
		   		+ "INSERT INTO reservation VALUES(104,'coupon',1,24000,5,'2021-05-26');\r\n"
		   		+ "INSERT INTO reservation VALUES(105,'card',1,27000,6,'2021-06-28');\r\n"
		   		+ "INSERT INTO reservation VALUES(106,'cash',0,43000,7,'2021-07-28');\r\n"
		   		+ "INSERT INTO reservation VALUES(107,'coupon',0,38000,8,'2021-08-28');\r\n"
		   		+ "INSERT INTO reservation VALUES(108,'card',1,21000,9,'2021-09-26');\r\n"
		   		+ "INSERT INTO reservation VALUES(109,'card',1,16000,10,'2021-10-27');\r\n"
		   		
		   		+ "INSERT INTO theater VALUES(1,250,1);\r\n"
		   		+ "INSERT INTO theater VALUES(2,260,1);\r\n"
		   		+ "INSERT INTO theater VALUES(3,240,1);\r\n"
		   		+ "INSERT INTO theater VALUES(4,230,1);\r\n"
		   		+ "INSERT INTO theater VALUES(5,220,1);\r\n"
		   		+ "INSERT INTO theater VALUES(6,200,1);\r\n"
		   		+ "INSERT INTO theater VALUES(7,150,1);\r\n"
		   		+ "INSERT INTO theater VALUES(8,270,1);\r\n"
		   		+ "INSERT INTO theater VALUES(9,280,1);\r\n"
		   		+ "INSERT INTO theater VALUES(10,210,1);\r\n"
		   		
		   		+ "INSERT INTO seat VALUES(111,10,0);\r\n"
		   		+ "INSERT INTO seat VALUES(112,10,1);\r\n"
		   		+ "INSERT INTO seat VALUES(121,9,1);\r\n"
		   		+ "INSERT INTO seat VALUES(122,9,0);\r\n"
		   		+ "INSERT INTO seat VALUES(131,8,1);\r\n"
		   		+ "INSERT INTO seat VALUES(132,8,0);\r\n"
		   		+ "INSERT INTO seat VALUES(141,7,0);\r\n"
		   		+ "INSERT INTO seat VALUES(142,7,1);\r\n"
		   		+ "INSERT INTO seat VALUES(151,6,1);\r\n"
		   		+ "INSERT INTO seat VALUES(152,6,0);\r\n"
		   		+ "INSERT INTO seat VALUES(161,5,1);\r\n"
		   		+ "INSERT INTO seat VALUES(162,5,0);\r\n"
		   		+ "INSERT INTO seat VALUES(171,14,1);\r\n"
		   		+ "INSERT INTO seat VALUES(172,14,0);\r\n"
		   		+ "INSERT INTO seat VALUES(181,3,0);\r\n"
		   		+ "INSERT INTO seat VALUES(182,3,1);\r\n"
		   		+ "INSERT INTO seat VALUES(191,2,1);\r\n"
		   		+ "INSERT INTO seat VALUES(192,2,0);\r\n"
		   		+ "INSERT INTO seat VALUES(201,1,0);\r\n"
		   		+ "INSERT INTO seat VALUES(202,1,1);\r\n"
		   		
		   		+ "INSERT INTO schedule VALUES(1,1,10,'2021-01-18','Monday',7,'15:30');\r\n"
		   		+ "INSERT INTO schedule VALUES(2,2,9,'2021-02-04','Tuesday',7,'7:00');\r\n"
		   		+ "INSERT INTO schedule VALUES(3,3,8,'2021-03-25','Thursday',7,'9:30');\r\n"
		   		+ "INSERT INTO schedule VALUES(4,4,7,'2021-04-26','Wednesday',7,'20:10');\r\n"
		   		+ "INSERT INTO schedule VALUES(5,5,6,'2021-05-04','Friday',7,'11:20');\r\n"
		   		+ "INSERT INTO schedule VALUES(6,6,5,'2021-06-26','Saturday',7,'11:00');\r\n"
		   		+ "INSERT INTO schedule VALUES(7,7,4,'2021-07-25','Monday',7,'12:00');\r\n"
		   		+ "INSERT INTO schedule VALUES(8,8,3,'2021-08-25','Sunday',7,'19:40');\r\n"
		   		+ "INSERT INTO schedule VALUES(9,9,2,'2021-09-25','Saturday',7,'17:00');\r\n"
		   		+ "INSERT INTO schedule VALUES(10,10,1,'2021-10-21','Wednesday',7,'12:50');\r\n"
		   		+ "INSERT INTO schedule VALUES(11,1,10,'2021-01-18','Monday',7,'18:30');\r\n"
		   		+ "INSERT INTO schedule VALUES(12,2,9,'2021-02-04','Tuesday',7,'10:00');\r\n"
		   		+ "INSERT INTO schedule VALUES(13,3,8,'2021-03-25','Thursday',7,'12:30');\r\n"
		   		+ "INSERT INTO schedule VALUES(14,4,7,'2021-04-26','Wednesday',7,'23:10');\r\n"
		   		+ "INSERT INTO schedule VALUES(15,5,6,'2021-05-04','Friday',7,'14:20');\r\n"
		   		+ "INSERT INTO schedule VALUES(16,6,5,'2021-06-26','Saturday',7,'14:00');\r\n"
		   		+ "INSERT INTO schedule VALUES(17,7,4,'2021-07-25','Monday',7,'15:00');\r\n"
		   		+ "INSERT INTO schedule VALUES(18,8,3,'2021-08-25','Sunday',7,'22:40');\r\n"
		   		+ "INSERT INTO schedule VALUES(19,9,2,'2021-09-25','Saturday',7,'20:00');\r\n"
		   		+ "INSERT INTO schedule VALUES(20,10,1,'2021-10-21','Wednesday',7,'15:50');\r\n"
		   		
		   		+ "INSERT INTO ticket VALUES(1,1,10,111,null,0,24000,24000);\r\n"
		   		+ "INSERT INTO ticket VALUES(2,1,10,112,100,0,24000,24000);\r\n"
		   		+ "INSERT INTO ticket VALUES(3,2,9,121,101,0,18000,16000);\r\n"
		   		+ "INSERT INTO ticket VALUES(4,2,9,122,null,0,18000,16000);\r\n"
		   		+ "INSERT INTO ticket VALUES(5,3,8,131,102,0,16000,14000);\r\n"
		   		+ "INSERT INTO ticket VALUES(6,3,8,132,null,0,16000,14000);\r\n"
		   		+ "INSERT INTO ticket VALUES(7,4,7,141,null,0,28000,22000);\r\n"
		   		+ "INSERT INTO ticket VALUES(8,4,7,142,103,0,28000,22000);\r\n"
		   		+ "INSERT INTO ticket VALUES(9,5,6,151,104,0,24000,24000);\r\n"
		   		+ "INSERT INTO ticket VALUES(10,5,6,152,null,0,24000,24000);\r\n"
		   		+ "INSERT INTO ticket VALUES(11,6,5,161,105,0,30000,27000);\r\n"
		   		+ "INSERT INTO ticket VALUES(12,6,5,162,null,0,30000,27000);\r\n"
		   		+ "INSERT INTO ticket VALUES(13,7,4,171,106,0,50000,43000);\r\n"
		   		+ "INSERT INTO ticket VALUES(14,7,4,172,null,0,50000,43000);\r\n"
		   		+ "INSERT INTO ticket VALUES(15,8,3,181,null,0,44000,38000);\r\n"
		   		+ "INSERT INTO ticket VALUES(16,8,3,182,107,0,44000,38000);\r\n"
		   		+ "INSERT INTO ticket VALUES(17,9,2,191,108,0,21000,21000);\r\n"
		   		+ "INSERT INTO ticket VALUES(18,9,2,192,null,0,21000,21000);\r\n"
		   		+ "INSERT INTO ticket VALUES(19,10,1,201,null,0,18000,16000);\r\n"
		   		+ "INSERT INTO ticket VALUES(20,10,1,202,109,0,18000,16000);\r\n"
		   		
		   		
		   		+ "SET SQL_MODE=@OLD_SQL_MODE;\r\n"
		   		+ "SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;\r\n"
		   		+ "SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;\r\n";
		   
		   String[] reset_query = query.split("\r\n");
		   
		   try {
			   Statement stmt = con.createStatement();
			   for(int i = 0; i < reset_query.length; i++) {
				   stmt.execute(reset_query[i]);
			   }
			   return "성공";
		   }catch(SQLException e) {
			   return e.getMessage();
		   }
	   }
	   
	   public static void main (String args[]) {
		   movie_project so = new movie_project();
	   }
}
