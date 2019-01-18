package me.NickUltracraft.Protect.Database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import me.NickUltracraft.Protect.Console;
import me.NickUltracraft.Protect.Main;
import me.NickUltracraft.Protect.Console.ConsoleLevel;

/**
 * A class Conex�o.java do projeto (PLUGIN - nProtect Rebuilt) pertence ao NickUltracraft
 * Discord: NickUltracraft#4550
 * Mais informa��es: https://nickuc.tk 
 *
 * Rebuild, do not copy
*/

public class Conex�o {
	
	public static Connection connectionCache = null;
	private ConnectionType type = ConnectionType.SQLITE;
	
	public enum ConnectionType {
		MYSQL, SQLITE;
	}
	public Conex�o(ConnectionType type) {
		this.type = type;
	}
	public Connection getConnection() {
		try {
			if(connectionCache == null || (connectionCache.isClosed())) {
				File db = new File(Main.m.getDataFolder(), "database.db");
				connectionCache = DriverManager.getConnection("jdbc:sqlite:" + db.toString());
			}
		} catch (Exception e) {
			new Console("Nao foi possivel conectar a database " + type.name(), ConsoleLevel.ERRO).sendMessage();
		}
		return connectionCache;
	}
	public void createDatabase() {
		try {
			Class.forName("org.sqlite.JDBC");
		    Statement stmt = getConnection().createStatement();
		    stmt.execute("CREATE TABLE IF NOT EXISTS `nProtect`(`ID` INTEGER PRIMARY KEY, `Usuario` TEXT, `Staffer` TEXT, `Senha` TEXT, `IP` TEXT);");
		    stmt.close();
		} catch (Exception e) {
			new Console("Nao foi possivel conectar a database " + type.name() + " e criar as tabelas", ConsoleLevel.ERRO).sendMessage();
		}
	}
	public void closeConnection() {
		try {
			if(connectionCache != null && (!connectionCache.isClosed())) {
				connectionCache.close();
			}
		} catch (Exception e) {
		}
	}
}
