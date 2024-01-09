package com.springdata.tennisplayer;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public String createTournamentTable() {
        String sql = "CREATE TABLE TOURNAMENT (ID INTEGER, NAME VARCHAR(50), LOCATION VARCHAR(50), PRIMARY KEY (ID))";
        jdbcTemplate.execute(sql);
        return "Table created";
    }

    public List<Player> getAllPlayers() {
        String sql = "SELECT * FROM PLAYER";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Player.class));
    }

    public Player getPlayerById(int id) {
        String sql = "SELECT * FROM PLAYER WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Player.class), id);
    }

    public int insertPlayer(Player player){
        String sql = "INSERT INTO PLAYER (ID, Name, Nationality, Birth_date, Titles) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                player.getId(), player.getName(), player.getNationality(),
                new Timestamp(player.getBirthDate().getTime()), player.getTitles());
    }

    public int updatePlayer(Player player){
        String sql = "UPDATE PLAYER SET Name = ?, Nationality = ?, Birth_date = ? , Titles = ? WHERE ID = ?";
        return jdbcTemplate.update(sql, player.getName(), player.getNationality(),
                new Timestamp(player.getBirthDate().getTime()), player.getTitles(), player.getId());
    }

    public int deletePlayerById(int id){
        String sql="DELETE FROM PLAYER WHERE ID = ?";
        return jdbcTemplate.update(sql, id);
    }
}