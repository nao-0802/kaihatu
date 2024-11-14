package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;

public class TeacherDao extends Dao {
    // SQLクエリ: teacher_idに基づいてレコードを取得
    private String baseSql = "SELECT teacher_id, teacher_name, password, class_id, flag FROM teachers WHERE teacher_id = ?";

    // ResultSetからTeacherリストを生成するメソッド
    private List<Teacher> postfilter(ResultSet rSet) throws Exception {
        List<Teacher> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(rSet.getString("teacher_id"));
                teacher.setTeacherName(rSet.getString("teacher_name"));
                teacher.setPassword(rSet.getString("password"));
                teacher.setClassId(rSet.getString("class_id"));
                teacher.setFlag(rSet.getInt("flag"));
                list.add(teacher);
            }
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

    // 指定されたteacher_idのTeacherを取得するメソッド
    public List<Teacher> filter(String teacherId) throws Exception {
        List<Teacher> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement(baseSql);
            statement.setString(1, teacherId);
            rSet = statement.executeQuery();
            list = postfilter(rSet);
        } catch (Exception e) {
            throw e;
        } finally {
            // PreparedStatementを閉じる
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            // Connectionを閉じる
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return list;
    }

    public List<Teacher> getAllTeachers() throws Exception {
        List<Teacher> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement("SELECT teacher_id, teacher_name, password, class_id, flag FROM teachers");
            rSet = statement.executeQuery();
            list = postfilter(rSet);
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return list;
    }

    // Teacherを保存または更新するメソッド
    public boolean save(Teacher teacher) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            // 既存のTeacherを検索
            Teacher existingTeacher = getTeacher(teacher.getTeacherId());
            if (existingTeacher == null) {
                // 新しいTeacherの場合
                statement = connection.prepareStatement(
                        "INSERT INTO teachers (teacher_id, teacher_name, password, class_id, flag) VALUES (?, ?, ?, ?, ?)"
                );
                statement.setString(1, teacher.getTeacherId());
                statement.setString(2, teacher.getTeacherName());
                statement.setString(3, teacher.getPassword());
                statement.setString(4, teacher.getClassId());
                statement.setInt(5, teacher.getFlag());
            } else {
                // 既存のTeacherの更新
                statement = connection.prepareStatement(
                        "UPDATE teachers SET teacher_name = ?, password = ?, class_id = ?, flag = ? WHERE teacher_id = ?"
                );
                statement.setString(1, teacher.getTeacherName());
                statement.setString(2, teacher.getPassword());
                statement.setString(3, teacher.getClassId());
                statement.setInt(4, teacher.getFlag());
                statement.setString(5, teacher.getTeacherId());
            }
            count = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return count > 0;
    }

    // teacher_idでTeacherを取得するメソッド
    private Teacher getTeacher(String teacherId) throws Exception {
        Teacher teacher = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement(baseSql);
            statement.setString(1, teacherId);
            rSet = statement.executeQuery();
            if (rSet.next()) {
                teacher = new Teacher();
                teacher.setTeacherId(rSet.getString("teacher_id"));
                teacher.setTeacherName(rSet.getString("teacher_name"));
                teacher.setPassword(rSet.getString("password"));
                teacher.setClassId(rSet.getString("class_id"));
                teacher.setFlag(rSet.getInt("flag"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return teacher;
    }

    // Teacherを削除するメソッド
    public boolean delete(String teacherId) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        boolean result = false;

        try {
            statement = connection.prepareStatement("DELETE FROM teachers WHERE teacher_id = ?");
            statement.setString(1, teacherId);
            statement.executeUpdate();
            result = true;
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return result;
    }
}