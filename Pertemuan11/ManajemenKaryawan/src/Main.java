/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Moch Zuhdi Maulana N
 */
import model.MyBatisUtil;
import model.ManajemenMapper;
import org.apache.ibatis.session.SqlSession;
import view.ManajemenView;
import controller.ManajemenController;

public class Main {
    public static void main(String[] args) {
        SqlSession session = MyBatisUtil.getSqlSession();
        ManajemenMapper mapper = session.getMapper(ManajemenMapper.class);

        ManajemenView view = new ManajemenView();
        new ManajemenController(view, mapper);

        view.setVisible(true);
    }
}

