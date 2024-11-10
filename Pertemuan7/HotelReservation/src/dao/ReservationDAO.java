package Pertemuan7.HotelReservation.src.dao;

import Pertemuan7.HotelReservation.src.db.MySqlConnection;
import Pertemuan7.HotelReservation.src.model.Reservation;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ReservationDAO {
    // Menambahkan reservasi baru
    public void addReservation(Reservation reservation) {
        String query = "INSERT INTO reservations (name, date, room_type, room_preference, facilities, guest_count, duration, payment_methods, review) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = MySqlConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, reservation.getName());

            // Mengonversi tanggal yang diterima menjadi format yang sesuai (YYYY-MM-DD)
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = sdf.parse(reservation.getDate()); // Mengubah string menjadi objek Date
            statement.setDate(2, new java.sql.Date(parsedDate.getTime())); // Menyimpan sebagai java.sql.Date

            statement.setString(3, reservation.getRoomType());
            statement.setString(4, reservation.getRoomPreference());
            statement.setString(5, reservation.getFacilities());
            statement.setInt(6, reservation.getGuestCount());
            statement.setInt(7, reservation.getDuration());

            String paymentMethods = String.join(", ", reservation.getPaymentMethods());
            statement.setString(8, paymentMethods);
            statement.setString(9, reservation.getReview());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Mendapatkan semua reservasi
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservations";
        try (Connection connection = MySqlConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(resultSet.getInt("id"));
                reservation.setName(resultSet.getString("name"));
                Date sqlDate = resultSet.getDate("date");
                reservation.setDate(sqlDate.toString());
                reservation.setRoomType(resultSet.getString("room_type"));
                reservation.setRoomPreference(resultSet.getString("room_preference"));
                reservation.setFacilities(resultSet.getString("facilities"));
                reservation.setGuestCount(resultSet.getInt("guest_count"));
                reservation.setDuration(resultSet.getInt("duration"));

                String paymentMethodsString = resultSet.getString("payment_methods");
                List<String> paymentMethods = new ArrayList<>();
                if (paymentMethodsString != null && !paymentMethodsString.isEmpty()) {
                    paymentMethods = Arrays.asList(paymentMethodsString.split(", "));
                }
                reservation.setPaymentMethods(paymentMethods);
                reservation.setReview(resultSet.getString("review"));
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    // Mendapatkan reservasi berdasarkan nama
    public Reservation getReservationByName(String name) {
        String query = "SELECT * FROM reservations WHERE name = ?";
        try (Connection connection = MySqlConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Reservation reservation = new Reservation();
                    reservation.setId(resultSet.getInt("id"));
                    reservation.setName(resultSet.getString("name"));
                    Date sqlDate = resultSet.getDate("date");
                    reservation.setDate(sqlDate.toString());
                    reservation.setRoomType(resultSet.getString("room_type"));
                    reservation.setRoomPreference(resultSet.getString("room_preference"));
                    reservation.setFacilities(resultSet.getString("facilities"));
                    reservation.setGuestCount(resultSet.getInt("guest_count"));
                    reservation.setDuration(resultSet.getInt("duration"));

                    String paymentMethodsString = resultSet.getString("payment_methods");
                    List<String> paymentMethods = new ArrayList<>();
                    if (paymentMethodsString != null && !paymentMethodsString.isEmpty()) {
                        paymentMethods = Arrays.asList(paymentMethodsString.split(", "));
                    }
                    reservation.setPaymentMethods(paymentMethods);
                    reservation.setReview(resultSet.getString("review"));
                    return reservation;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Memperbarui data reservasi yang ada
    public void updateReservation(Reservation reservation) {
        String query = "UPDATE reservations SET date = ?, room_type = ?, room_preference = ?, facilities = ?, guest_count = ?, duration = ?, payment_methods = ?, review = ? WHERE name = ?";
        try (Connection connection = MySqlConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Mengonversi tanggal yang diterima menjadi format yang sesuai (YYYY-MM-DD)
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = sdf.parse(reservation.getDate()); // Mengubah string menjadi objek Date
            statement.setDate(1, new java.sql.Date(parsedDate.getTime())); // Menyimpan sebagai java.sql.Date

            statement.setString(2, reservation.getRoomType());
            statement.setString(3, reservation.getRoomPreference());
            statement.setString(4, reservation.getFacilities());
            statement.setInt(5, reservation.getGuestCount());
            statement.setInt(6, reservation.getDuration());

            String paymentMethods = String.join(", ", reservation.getPaymentMethods());
            statement.setString(7, paymentMethods);
            statement.setString(8, reservation.getReview());
            statement.setString(9, reservation.getName());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Menghapus reservasi berdasarkan nama
    public void deleteReservation(String name) {
        String query = "DELETE FROM reservations WHERE name = ?";
        try (Connection connection = MySqlConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
