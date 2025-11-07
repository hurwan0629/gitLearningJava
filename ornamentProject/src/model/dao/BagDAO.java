package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBCUtil;
import model.dto.BagDTO;
import model.dto.ProductDTO;

public class BagDAO {
	
	private static final String SELECT_ALL_PRODUCT_IN_OWNER_BAG
			= "SELECT B.BAG_PK, B.PRODUCT_PK, B.PRODUCT_COUNT AS BAG_PRODUCT_COUNT, B.MEMBER_PK, "
					+ "P.PRODUCT_NAME, P.PRODUCT_PRICE, P.PRODUCT_COUNT, P.PRODUCT_BRAND"
				+ "FROM BAG B INNER JOIN PRODUCT P ON B.PRODUCT_PK = P.PRODUCT_PK WHERE B.MEMBER_PK = ?";
	
	private static final String INSERT_PRODUCT_AND_COUNT_INTO_BAG
			= "INSERT INTO BAG(BAG_PK, PRODUCT_PK, PRODUCT_COUNT, MEMBER_PK) "
					+ "VALUES(BAG_SEQ.NEXTVAL, ? , ? , ? )";
	private static final String DELETE_CLEAR_MEMBER_BAG
			= "DELETE FROM BAG WHERE MEMBER_PK = ?";
	
	private BagDTO selectOne(BagDTO bagDTO) {
		return null;
	}
	
	public ArrayList<BagDTO> selectAll(BagDTO bagDTO){
		// 로그
		System.out.print("BagDAO.selectAll [로그] ");
		System.out.println(bagDTO);
		////////////////////////////////////////////////////////////////////
		Connection conn = JDBCUtil.connect();
		// return datas;
		ArrayList<BagDTO> datas = new ArrayList<>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(this.SELECT_ALL_PRODUCT_IN_OWNER_BAG);
			pstmt.setInt(1, bagDTO.getMemberPk());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				bagDTO.setBagPk(rs.getInt("BAG_PK"));
				bagDTO.setMemberPk(rs.getInt("MEMBER_PK"));
				bagDTO.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				bagDTO.setProductBrand(rs.getString("PRODUCT_BRAND"));
				bagDTO.setProductCount(rs.getInt("PRODUCT_COUNT"));
				bagDTO.setProductName(rs.getString("PRODUCT_NAME"));
				bagDTO.setProductPk(rs.getInt("PRODUCT_PK"));
				
				datas.add(bagDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.print("BagDAO.selectAll [반환 로그] ");
		return datas;
	}
	
	public boolean insert(BagDTO bagDTO) {
		// productPk, productConut , memebrPk,추가
		// 로그
		System.out.print("BagDAO.insert [시작 로그] bagDTO:");
		System.out.println(bagDTO);
		////////////////////////////////////////////////////////////////////
		Connection conn = JDBCUtil.connect();
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(this.INSERT_PRODUCT_AND_COUNT_INTO_BAG);
			pstmt.setInt(1, bagDTO.getProductPk());
			pstmt.setInt(2, bagDTO.getProductCount());
			pstmt.setInt(3, bagDTO.getMemberPk());
			int rs = pstmt.executeUpdate();
			if(rs <= 0) {
				System.out.print("BagDAO.insert [실패 로그] ");
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("BagDAO.insert [성공 로그] ");
		return true;
	}
	
	private boolean update(BagDTO bagDTO) {
		return false;
	}
	
	private boolean delete(BagDTO bagDTO) {
		
		System.out.print("BagDAO.delete [시작 로그] ");
		System.out.println(bagDTO);
		////////////////////////////////////////////////////////////////////
		Connection conn = JDBCUtil.connect();
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(this.DELETE_CLEAR_MEMBER_BAG);
			pstmt.setInt(1, bagDTO.getMemberPk());
			int rs = pstmt.executeUpdate();
			
			if(rs <= 0) {
				System.out.print("BagDAO.delete [실패 로그] ");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.print("BagDAO.delete [성공 로그] ");
		return true;
	}
}
