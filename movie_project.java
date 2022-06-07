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
			setTitle("18011828/������");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container c = getContentPane();
			c.setLayout(new FlowLayout());
			sqlReady();
			
			JButton btn_mainPage = new JButton("ù ȭ������ ���ư���");
			JButton btn_manager = new JButton("������");
			JButton btn_member = new JButton("ȸ��");
			JButton btn_manager_1 = new JButton("�Է�");
			JButton btn_manager_3 = new JButton("����");
			JButton btn_manager_4 = new JButton("����");
			JButton btn_manager_2 = new JButton("��ü ���̺� ����");
			JButton btn_manager_5 = new JButton("�ʱ�ȭ");
			
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
			
			/*----------------------------�����ڱ��-------------------------*/
			btn_manager_1.addActionListener(new ActionListener() { //�Է�
				public void actionPerformed(ActionEvent e) {
					btn_mainPage.setVisible(false);
					btn_manager_1.setVisible(false);
					btn_manager_3.setVisible(false);
					btn_manager_4.setVisible(false);
					btn_manager_2.setVisible(false);
					btn_manager_5.setVisible(false);
					
					JButton exit = new JButton("������");
					JLabel text_label = new JLabel("");
				
					
					JTextField manager_query = new JTextField(50);
					JButton btn_submit = new JButton("�Է�_submit");
					c.add(manager_query);
					c.add(btn_submit);
					
					btn_submit.addActionListener(new ActionListener() { // ������1
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
			
			btn_manager_3.addActionListener(new ActionListener() { //����
				public void actionPerformed(ActionEvent e) {
					btn_mainPage.setVisible(false);
					btn_manager_1.setVisible(false);
					btn_manager_3.setVisible(false);
					btn_manager_4.setVisible(false);
					btn_manager_2.setVisible(false);
					btn_manager_5.setVisible(false);
					
					JButton exit = new JButton("������");
					JLabel text_label = new JLabel();
				
					
					JTextField manager_query_table = new JTextField(10);
					JTextField manager_query_condition = new JTextField(50);
					manager_query_table.setText("����� table��");
					manager_query_condition.setText("����� ������");
					JButton btn_submit = new JButton("����_submit");
					c.add(manager_query_table);
					c.add(manager_query_condition);
					c.add(btn_submit);
					
					btn_submit.addActionListener(new ActionListener() { // ������1
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
			
			btn_manager_5.addActionListener(new ActionListener() { // ������1
				public void actionPerformed(ActionEvent e) {
					btn_mainPage.setVisible(false);
					btn_manager_1.setVisible(false);
					btn_manager_3.setVisible(false);
					btn_manager_4.setVisible(false);
					btn_manager_2.setVisible(false);
					btn_manager_5.setVisible(false);
					
					JButton exit = new JButton("������");
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
			
			btn_manager_4.addActionListener(new ActionListener() { //����
				public void actionPerformed(ActionEvent e) {
					btn_mainPage.setVisible(false);
					btn_manager_1.setVisible(false);
					btn_manager_3.setVisible(false);
					btn_manager_4.setVisible(false);
					btn_manager_2.setVisible(false);
					btn_manager_5.setVisible(false);
					
					JButton exit = new JButton("������");
					JLabel text_label = new JLabel();
				
					
					JTextField manager_query_table = new JTextField(10);
					JTextField manager_query_set = new JTextField(50);
					JTextField manager_query_condition = new JTextField(50);
					manager_query_table.setText("����� table��");
					manager_query_set.setText("����� �ٲٴ� ���� (set ������ �� ����)");
					manager_query_condition.setText("����� ������");
					JButton btn_submit = new JButton("����_submit");
					c.add(manager_query_table);
					c.add(manager_query_set);
					c.add(manager_query_condition);
					c.add(btn_submit);
					
					btn_submit.addActionListener(new ActionListener() { // ������1
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
					
					JButton exit = new JButton("������");
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
			/*----------------------------�����ڱ��-------------------------*/
			
			
			/*----------------------------ȸ�����-------------------------*/
			JButton movie_table = new JButton("��ȭ ��ȸ");
			JButton reservation_check = new JButton("���� Ȯ��");
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
					JLabel info = new JLabel("ȸ�����̵� �Է��ϼ���!");
					JTextField tx_member_id = new JTextField(5);
					JButton btn_member_id = new JButton("submit");
					c.add(info);
					c.add(tx_member_id);
					c.add(btn_member_id);
					
					btn_member_id.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							member_id = Integer.parseInt(tx_member_id.getText());
							lb_member_id.setText("ȸ�� ���̵� : " + Integer.toString(member_id));
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
					
					JLabel lb_movie = new JLabel("������� : ��ȭ��, ������, ����, �帣�� �Է��ϼ���.");
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
					
					JTextField tf_reservation_movie_id = new JTextField(5); //������ ��ȭ ��ȣ �Է��ϴ� text field
					JButton btn_reservation_movie_id = new JButton("text field�� �����ϰ� ���� movie_id�� �Է��� �� ��ư�� ��������.");
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
					JButton btn_detail = new JButton("�������� Ȯ���ϱ�");
					JButton btn_remove = new JButton("���� �����ϱ�");
					JButton btn_change_movie = new JButton("�ٸ���ȭ�� �ٲٱ�");
					JButton btn_change_schedule = new JButton("������ �����ϱ�");
					
					btn_detail.addActionListener(new ActionListener() { //�������� Ȯ���ϱ�
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
					
					btn_remove.addActionListener(new ActionListener() { //���Ż���
						public void actionPerformed(ActionEvent e) {
							reserved_movie.setVisible(true);
							btn_detail.setVisible(false);
							btn_remove.setVisible(false);
							btn_change_movie.setVisible(false);
							btn_change_schedule.setVisible(false);
							
							JTextField tf_remove = new JTextField(5);
							JButton btn_remove = new JButton("������ reservation_id�� ���� ��ư�� ��������.");
							
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
					
					btn_change_movie.addActionListener(new ActionListener() { //�ٸ���ȭ�� �ٲٱ�
						public void actionPerformed(ActionEvent e) {
							reserved_movie.setVisible(true);
							btn_detail.setVisible(false);
							btn_remove.setVisible(false);
							btn_change_movie.setVisible(false);
							btn_change_schedule.setVisible(false);
							
							JTextField tf_reservation_id = new JTextField(30);
							tf_reservation_id.setText("�����ϰ� ���� reservation_id�� �Է��ϼ���.");
							JTextField tf_movie_id = new JTextField(30);
							tf_movie_id.setText("�����ϰ� ���� movie_id�� �Է��ϼ���.");
							JButton btn_remove = new JButton("submit");
							
							btn_remove.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									String reservation_id = tf_reservation_id.getText();
									String movie_id = tf_movie_id.getText();
									JLabel lb_result = new JLabel();
									if(reservation_movie(movie_id).equals("����")) {
										reservation_delete(reservation_id);
										lb_result.setText("����");
									}
									else {
										lb_result.setText("�����Դϴ�.");
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
					
					btn_change_schedule.addActionListener(new ActionListener() { //������ �����ϱ�
						public void actionPerformed(ActionEvent e) {
							reserved_movie.setVisible(true);
							btn_detail.setVisible(false);
							btn_remove.setVisible(false);
							btn_change_movie.setVisible(false);
							btn_change_schedule.setVisible(false);
							JLabel lb_schedule_check = new JLabel(reservation_schedule_check());
							
							JTextField tf_reservation_id = new JTextField(30);
							JTextField tf_schedule_id = new JTextField(30);
							tf_reservation_id.setText("������ reservation_id�� �̰��� �Է��ϼ���.");
							tf_schedule_id.setText("������ ���ϴ� schedule_id�� �̰��� �Է��ϼ���.");
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
		     try { /* ����̹��� ã�� ���� */
		       Class.forName("com.mysql.cj.jdbc.Driver");   
		       System.out.println("����̹� �ε� ����");
		     } catch(ClassNotFoundException e) {
		         e.printStackTrace();
		      }    
		     try { /* �����ͺ��̽��� �����ϴ� ���� */
		       System.out.println("�����ͺ��̽� ���� �غ�...");	
		       con=DriverManager.getConnection(url, userid, pwd);
		       System.out.println("�����ͺ��̽� ���� ����");
		     } catch(SQLException e) {
		         e.printStackTrace();
		       }
		  	}
	   
	   private String manager_1(String query) { //�Է�
		  	  try { /* �����ͺ��̽��� ���� ����� �������� ���� */
				   Statement stmt = con.createStatement();
				   
				   stmt.execute(query);
	
			  	  } catch(SQLException e) {
			          return e.getMessage();
			  	   } catch(Exception e) {
			  		   return e.toString();
			  	   }
			  	  return "����";
	   }
	   
	   private String manager_3(String table, String condition) { //����
		  	  try { /* �����ͺ��̽��� ���� ����� �������� ���� */
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
			  	  return "����";
	   }
	   
	   private String manager_4(String table, String condition, String set) { //����
		  	  try { /* �����ͺ��̽��� ���� ����� �������� ���� */
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
			  	  return "����";
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
		  	  		 return "�����Դϴ�.";
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
		  	  	 
		  	  	 return "����";
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
			  	 return "����"; 
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
			   return "��������";
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
		   		+ "-- madang ���� �ڵ带 ������\r\n"
		   		+ "-- -----------------------------------------------------\r\n"
		   		+ "DROP DATABASE IF EXISTS  madang;\r\n"
		   		+ "create database madang;\r\n"
		   		+ "grant all privileges on madang.* to madang@localhost with grant option;\r\n"
		   		+ "commit;\r\n"
		   		+ "-- -----------------------------------------------------\r\n"
		   		+ "-- madang ���� �ڵ带 ������\r\n"
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
		   		
		   		+ "INSERT INTO movie VALUES(1,'���˵���2','1:46','15�� ������','�̻��','������','�׼�','��õ�� ���¹��� ��Ʈ������ ������ �����ڸ� �ε��޾� ����� �̼��� �޴µ�, ���� �����ڿ��Լ� �������� ������, ������ ���̴� �����ػ��� �Ѵ´�','2021-01-18');            \r\n"
		   		+ "INSERT INTO movie VALUES(2,'���� ��Ʈ������','2:06','12�� ������','�� ���̹�','���׵�Ʈ �Ĺ���ġ','��Ÿ��','������ �տ��Ǵ� ������ �ھ�Ų �ð����� ��Ƽ������ ������ ���� �����, �׸��� ������ �Ѿ� ���� ���ο� ������� �´ڶ߸��� �� ���� ��Ʈ������!','2021-02-04');            \r\n"
		   		+ "INSERT INTO movie VALUES(3,'�ȳ��ϼ���','1:58','12�� ������','������','��ȯ��','���','�ܷο� ���󿡼� ������ ����� ����ȩ ����. �״� ���� �˷��ְڴٴ� ������ ���ȿ� ���� ȣ���ǽ� ������ ã�ư���. ����ġ ���� �����԰� �������� ���̸� �ݱ�µ�..','2021-03-25');            \r\n"
		   		+ "INSERT INTO movie VALUES(4,'���� �̾�','1:30','��ü ������','�� ���ŷ�','�ٳ׻� �Ķ��','�ִϸ��̼�','�ĸ��� ���ÿ��� ��ݵ� �̽��͸� ������ ������ϴ�. �ҹ��� ���ΰ��� ������ ������ �ܸ� ������ �ѱ�� �״� ���� ����� ���� ������ �� ������ �����ϰ� �Ǵµ�..','2021-04-26');            \r\n"
		   		+ "INSERT INTO movie VALUES(5,'��� ������','1:40','��ü ������','�ǿ��� �丮��','�� ����','�ִϸ��̼�','�Ϻ��� ���÷��̸� ��ġ�� �ְ��� ���� �༮���� �Ǽ��� ü���ȴ�. �׵鵵 ������ �� �ִٴ� �ڻ��� �������� ���� �༮���� �ٸ� ��Ȱ ���� ������Ʈ�� ���Եǰ� �Ǵµ�...','2021-05-04');            \r\n"
		   		+ "INSERT INTO movie VALUES(6,'������','1:48','12�� ������','�ż���','������','���','����� �ʸ��� ã�� ȫ������ ������ ������ ���󰡴� ������ �� �ð� ���� �����ϰ� �Ǵµ�... ��¾��, ������� �ް� ��ȭ�� ���� ������ �ǻ�Ƴ��� �͸� ����.','2021-06-26');            \r\n"
		   		+ "INSERT INTO movie VALUES(7,'�Ǵ� ������ ���ϴ�','1:30','15�� ������','����','������','�׼�','�������� �����Ĵ� �Ǹ� ���� �ذ��, �ϸ� ������ �ռ��� �ϴ븦 ����Ѵ�. �׷���, ���Ͽ� ���� �������� ������ ���� ��� ������Ȱ�� �ϰ� �Ǵµ�..','2021-07-25');            \r\n"
		   		+ "INSERT INTO movie VALUES(8,'�� ���','1:37','15�� ������','�ش�����','�̻级 �۸�','������','���� ���Ի� ���˷������� ���� �����ο� ������ �� ���޻� �����̡̹����� ������� ������. �ְ� �����ϴ� ���˷������� �� 1���� ��� ���� �Ʒ��� �ŵ��ϴµ�����.','2021-08-25');            \r\n"
		   		+ "INSERT INTO movie VALUES(9,'�÷��̱׶���','1:12','12�� ������','�ζ� �ϵ�','���� �ݵ���ũ','���','�ϰ� �� ���� ���� �б��� ������. �ƺ��� ���� ��� ���������� ��� �ƺ��� �б��� �ηƴ�. ���� �쿬�� �ƺ��� ģ���鿡�� ������ ���ϴ� �� ����Ѵ�.','2021-09-25');            \r\n"
		   		+ "INSERT INTO movie VALUES(10,'����','1:58','15�� ������','��ī�� �ϳ���','�ٴϿ� ������','���','� ����, ����� �;��� ����� �Ǹ��� �Ǿ� �׿��� ���ƿ��� ������� ������ ���� ã�� ������ �ȴ�. ������ �˾Ƴ� ����, ������� ���� ������ ���ǰ� �����ϰ� �Ǵµ�..','2021-10-21');   \r\n"
		   		
		   		
		   		+ "INSERT INTO member VALUES(1,'������','010-7283-2849','ygh@gmail.com');\r\n"
		   		+ "INSERT INTO member VALUES(2,'������','010-3058-1482','sys@gmail.com');\r\n"
		   		+ "INSERT INTO member VALUES(3,'��μ�','010-3738-1339','kms@gmail.com');\r\n"
		   		+ "INSERT INTO member VALUES(4,'���α�','010-4800-9380','kmg@gmail.com');\r\n"
		   		+ "INSERT INTO member VALUES(5,'������','010-1038-3842','nym@gmail.com');\r\n"
		   		+ "INSERT INTO member VALUES(6,'������','010-1125-9955','dmh@gmail.com');\r\n"
		   		+ "INSERT INTO member VALUES(7,'������','010-2201-7787','myg@gmail.com');\r\n"
		   		+ "INSERT INTO member VALUES(8,'�ں���','010-1313-9471','ppm@gmail.com');\r\n"
		   		+ "INSERT INTO member VALUES(9,'������','010-1098-2312','sjy@gmail.com');\r\n"
		   		+ "INSERT INTO member VALUES(10,'�ȼ���','010-3829-3662','asy@gmail.com');\r\n"
		   		
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
			   return "����";
		   }catch(SQLException e) {
			   return e.getMessage();
		   }
	   }
	   
	   public static void main (String args[]) {
		   movie_project so = new movie_project();
	   }
}
