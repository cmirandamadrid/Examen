package com.petSmile.ReservaHora;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class App 
{
	private static final String DB_URL="jdbc:mysql://localhost:3306/clinica";
	private static final String DB_USER="root";
	private static final String DB_PASSWORD="12345678";
	
	public static boolean validarCredenciales(String nombreUsuario, String password) {
		try {
			Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
			String query = "SELECT nombreUsuario FROM usuario WHERE password=? AND password=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, nombreUsuario);
			preparedStatement.setString(2, password);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			boolean isValid = resultSet.next();
			
			resultSet.close();
			preparedStatement.close();
			connection.close();
			
			return isValid;
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		String inputNombreUsuario = "usuario_ingresado";
		String inputPassword = "contraseña_ingresada";
		
		if (validarCredenciales(inputNombreUsuario,inputPassword)) {
			System.out.println("Inicio de sesión exitoso");
		}else {
			System.out.println("Credenciales inválidas");
		}
	}
}
