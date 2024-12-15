/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Moch Zuhdi Maulana N
 */

import org.apache.ibatis.annotations.*;
import java.util.List;

public interface ManajemenMapper {

    // Mendapatkan semua data karyawan
    @Select("SELECT * FROM karyawan")
    List<Manajemen> getAllEmployees();

    // Menambahkan data karyawan baru
    @Insert("INSERT INTO karyawan (name, position, salary, status) VALUES (#{name}, #{position}, #{salary}, #{status})")
    void insertEmployee(Manajemen manajemen);

    // Memperbarui data karyawan berdasarkan ID
    @Update("UPDATE karyawan SET name = #{name}, position = #{position}, salary = #{salary}, status = #{status} WHERE id = #{id}")
    void updateEmployee(Manajemen manajemen);

    // Menghapus data karyawan berdasarkan ID
    @Delete("DELETE FROM karyawan WHERE id = #{id}")
    void deleteEmployeeById(int id);
}


