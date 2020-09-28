package com.ui;

import com.module.Class;
import com.services.ClassService;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

public class MainUI extends JFrame {
//1. void mouseClicked(MouseEvent e): Được triệu hồi khi nút chuột đã được click (được nhấn và nhả ra) trên một thành phần.
//
//            2. void mouseEntered(MouseEvent e): Được triệu hồi khi chuột nhập vào một thành phần.
//
//            3. void mouseExited(MouseEvent e): Được triệu hồi khi chuột thoát ra khỏi một thành phần.
//
//4. void mousePressed(MouseEvent e): Được triệu hồi khi một nút chuột đã được nhấn trên một thành phần.
//
//            5. void mouseReleased(MouseEvent e): Được triệu hồi khi một nút chuột đã được nhả ra trên một thành phần.

    ClassService classService = new ClassService();
    JTextField txtId = new JTextField(55);
    JTextField txtName = new JTextField(55);
    JTextField txtTeacher = new JTextField(55);

    JLabel lblId = new JLabel("Mã số lớp: ");
    JLabel lblName = new JLabel("Tên lớp: ");
    JLabel lblTeacher = new JLabel("Giáo viên CN: ");

    JButton btnAdd = new JButton("Thêm");
    JButton btnSave = new JButton("Lưu");
    JButton btnCancel = new JButton("Hủy");
    JButton btnDelete = new JButton("Xóa");


    JPanel pnInputData;


    DefaultTableModel defaultTableModel = new DefaultTableModel();
    //JTable
    JTable tbl;

    public MainUI(String title){
        super(title);
        addControls();//controls
        addEvents();//xu ly su kien
        showTable();//hien thi thoong tin tren database
        invisableControls();//an button
    }


    private void addControls() {
        Container container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        //panel tieu de
        JPanel pnTitle = new JPanel();
        JLabel lblTitle = new JLabel("THÔNG TIN LỚP HỌC");
        lblTitle.setFont(new Font("tahoma", Font.BOLD, 30));
        lblTitle.setForeground(Color.RED);
        pnTitle.add(lblTitle);
        container.add(pnTitle);

        //ke vien border
        Border border = BorderFactory.createLineBorder(Color.BLUE);
        TitledBorder showTableTitledBorder =new TitledBorder(border,"Danh sách lớp học");
        //panel nhap du lieu
        pnInputData = new JPanel();
        pnInputData.setLayout(new BoxLayout(pnInputData, BoxLayout.Y_AXIS));
        container.add(pnInputData);

        // add class id
        JPanel pnId = pn(lblId, txtId);
        pnInputData.add(pnId);
        // add classname
        JPanel pnName = pn(lblName, txtName);
        pnInputData.add(pnName);
        // add teacher
        JPanel pnTeacher = pn(lblTeacher, txtTeacher);
        pnInputData.add(pnTeacher);

        // căn chỉnh, ma so lop dai nhat ne lay theo ma so lop
        lblId.setPreferredSize(lblTeacher.getPreferredSize());
        lblName.setPreferredSize(lblTeacher.getPreferredSize());
        //panel nut lenh
        JPanel pnButton = new JPanel();
        pnButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnButton.add(btnAdd);
        pnButton.add(btnSave);
        pnButton.add(btnCancel);
        pnButton.add(btnDelete);
        container.add(pnButton);

        // bang hien thi
        JPanel pnShow = new JPanel();
        pnShow.setLayout(new BorderLayout());
        container.add(pnShow);
        pnShow.setBorder(showTableTitledBorder);

        defaultTableModel.addColumn("Mã số lớp");
        defaultTableModel.addColumn("Tên lớp");
        defaultTableModel.addColumn("Giáo viên chủ nhiệm");

        tbl = new JTable(defaultTableModel);
        tbl.getTableHeader().setForeground(Color.BLUE);
        tbl.getTableHeader().setFont(new Font("tahoma", Font.BOLD, 16));

        JScrollPane sc = new JScrollPane(tbl, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pnShow.add(sc, BorderLayout.CENTER);



    }

    private void addEvents() {
        //hủy
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cancelEvent();
            }
        });
        //xóa
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    deleteEvent();
                }catch (NumberFormatException e1){
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn lớp để xóa!");
                }

            }
        });
        //lưu
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                saveEvent();
            }
        });
        //thêm
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                addButtonEvents();
            }
        });
        tbl.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                clickEvent();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void clickEvent() {
        int row =tbl.getSelectedRow();
        TableModel model = tbl.getModel();
        btnSave.setEnabled(true);
        btnCancel.setEnabled(true);
        btnAdd.setEnabled(true);
        btnDelete.setEnabled(true);
        pnInputData.setEnabled(true);
        txtId.setEnabled(true);
        txtName.setEnabled(true);
        txtTeacher.setEnabled(true);
        txtId.setText(model.getValueAt(row, 0).toString());
        txtName.setText(model.getValueAt(row,1).toString());
        txtTeacher.setText(model.getValueAt(row, 2).toString());
    }

    private void addButtonEvents() {
        pnInputData.setEnabled(true);
        txtId.setEnabled(true);
        txtName.setEnabled(true);
        txtTeacher.setEnabled(true);
        btnSave.setEnabled(true);
        btnCancel.setEnabled(true);
        btnAdd.setEnabled(false);
        btnDelete.setEnabled(false);

    }

    private void saveEvent() {
        Class c = new Class();
        c.setId(Integer.parseInt(txtId.getText()));
        c.setName(txtName.getText());
        c.setTeacher(txtTeacher.getText());
        if (classService.checkUnique(Integer.parseInt(txtId.getText()))) {
            //truong hop sua
            int updateConfirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa lớp này không ?", "Xác nhận sửa!", JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            if (updateConfirm==JOptionPane.YES_OPTION){
                int x = classService.updateClass(c);
                if (x>0){
                    JOptionPane.showMessageDialog(null,"Sửa thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    showTable();
                    txtId.setText("");
                    txtName.setText("");
                    txtTeacher.setText("");
                }else {
                    JOptionPane.showMessageDialog(null,"Sửa thất bại");
                }

            }
        }else {
            int addConfirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm lớp này không!", "Xác nhận thêm!", JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            if (addConfirm==JOptionPane.YES_OPTION){
                int x = classService.addClass(c);
                if (x>0){
                    JOptionPane.showMessageDialog(null,"Thêm thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    showTable();
                    txtId.setText("");
                    txtName.setText("");
                    txtTeacher.setText("");

                }else {
                    JOptionPane.showMessageDialog(null,"thêm thất bại");
                }
            }
        }
    }

    private void deleteEvent() {
        int deleteConfirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không ?", "Xác nhận xóa!", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
        if (deleteConfirm == JOptionPane.YES_OPTION){
            int id = Integer.parseInt(txtId.getText());
            int x = classService.deleteClass(id);
            if (x>0){
                JOptionPane.showMessageDialog(null,"Xóa thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                showTable();
                txtId.setText("");
                txtName.setText("");
                txtTeacher.setText("");
            }else {
                JOptionPane.showMessageDialog(null,"Xóa thất bại");

            }

        }
    }

    private void cancelEvent() {
        txtId.setText("");
        txtName.setText("");
        txtTeacher.setText("");
        txtId.setEnabled(false);
        txtName.setEnabled(false);
        txtTeacher.setEnabled(false);
        pnInputData.setEnabled(false);
        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);
        btnAdd.setEnabled(true);
        btnDelete.setEnabled(true);
    }

    private void showTable() {
        //hien thi du lieu tu database len
        ArrayList<Class> classes = classService.displayJTable();
        defaultTableModel.setRowCount(0);
        for (Class x : classes){
            Vector<Object> vec = new Vector<>();
            vec.add(x.getId());
            vec.add(x.getName());
            vec.add(x.getTeacher());
            defaultTableModel.addRow(vec);
        }
    }

    private void invisableControls() {
        txtId.setEnabled(false);
        txtName.setEnabled(false);
        txtTeacher.setEnabled(false);
        pnInputData.setEnabled(false);
        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);
    }

    public JPanel pn(JLabel lbl, JTextField txt) {
        JPanel pn = new JPanel();
        pn.setLayout(new FlowLayout(FlowLayout.CENTER));
        pn.add(lbl);
        pn.add(txt);
        return pn;

    }

    public void showWindow() {
        this.setSize(800, 400);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }



}
