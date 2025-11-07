package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBCUtil;
import model.crawling.CrawlingProducts;
import model.dto.ProductDTO;

public class ProductDAO {
	private static final String BRAND_ALL = "SELECT DISTINCT PRODUCT_BRAND FROM PRODUCT";
	private static final String PRODUCT_ALL = "SELECT PRODUCT_PK, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_COUNT, "
			+ "PRODUCT_BRAND FROM PRODUCT ORDER BY PRODUCT_PK DESC";
	private static final String PRODUCT_SEARCH = "SELECT PRODUCT_PK, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_COUNT, "
			+ "PRODUCT_BRAND FROM PRODUCT WHERE PRODUCT_NAME LIKE ?";
	private static final String PRODUCT_ALL_DOWN = "SELECT PRODUCT_PK, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_COUNT, PRODUCT_BRAND"
			+ " FROM PRODUCT ORDER BY PRODUCT_PRICE DESC";
	private static final String PRODUCT_ALL_UP = "SELECT PRODUCT_PK, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_COUNT, PRODUCT_BRAND"
			+ " FROM PRODUCT ORDER BY PRODUCT_PRICE";
	private static final String PRODUCT_BRAND = "SELECT PRODUCT_PK, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_COUNT, PRODUCT_BRAND"
			+ " FROM PRODUCT WHERE PRODUCT_BRAND = ?";

	private static final String PRODUCT_SELECT = "SELECT PRODUCT_PK, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_COUNT, PRODUCT_BRAND "
			+ "FROM PRODUCT WHERE PRODUCT_PK = ?";

	private static final String INSERT_PRODUCT = "INSERT INTO PRODUCT(PRODUCT_PK, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_COUNT, PRODUCT_BRAND)"
			+ " VALUES(PRODUCT_SEQ.NEXTVAL,?,?,?,?)";

	private static final String UPDATE_ADD = "UPDATE PRODUCT SET PRODUCT_COUNT = PRODUCT_COUNT + ? WHERE PRODUCT_PK = ?";
	private static final String UPDATE_BUY = "UPDATE PRODUCT SET PRODUCT_COUNT = PRODUCT_COUNT - ? WHERE PRODUCT_PK = ?;";

	private static final String DELETE_PRODUCT = "DELETE FROM PRODUCT WHERE PRODUCT_PK = ?";

	
	
	ProductDAO(){
		CrawlingProducts.CrawlProducts();
	}
	
	
	
	
	// 상품 전체 보기
	public ArrayList<ProductDTO> selectAll(ProductDTO productDTO) {
		ArrayList<ProductDTO> datas = new ArrayList<ProductDTO>(); // 보낼 데이터의 배열

		// 1,2드라이버 로드 db 연결
		Connection conn = JDBCUtil.connect();

		// 3 db 사용
		PreparedStatement pstmt = null;
		try {
			if (productDTO.getCondition().equals("BRAND_UNIQUE")) { // 브랜드 전체 데이터
				pstmt = conn.prepareStatement(BRAND_ALL);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					ProductDTO data = new ProductDTO();
					data.setProductBrand(rs.getString("PRODUCT_BRAND")); // 상품 브랜드
					datas.add(data); // 위의 배열에 정보 삽입
				}
			} else if (productDTO.getCondition().equals("ALL_DESC")) { // PK 내림차순 전체 데이터
				pstmt = conn.prepareStatement(PRODUCT_ALL);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					ProductDTO data = new ProductDTO();
					data.setProductPK(rs.getInt("PRODUCT_PK")); // 상품번호(PK)
					data.setProductName(rs.getString("PRODUCT_NAME")); // 상품 이름
					data.setProductPrice(rs.getInt("PRODUCT_PRICE")); // 상품 가격
					data.setProductCount(rs.getInt("PRODUCT_COUNT")); // 상품재고
					data.setProductBrand(rs.getString("PRODUCT_BRAND")); // 상품 브랜드
					datas.add(data); // 위의 배열에 정보 삽입
				}
			} else if (productDTO.getCondition().equals("ALL_SEARCH")) { // 해당 검색 포함한 상품 출력
				pstmt = conn.prepareStatement(PRODUCT_SEARCH);
				pstmt.setString(1, productDTO.getKeyword()); // 키워드(검색어) 받아야함
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					ProductDTO data = new ProductDTO();
					data.setProductPK(rs.getInt("PRODUCT_PK")); // 상품번호(PK)
					data.setProductName(rs.getString("PRODUCT_NAME")); // 상품 이름
					data.setProductPrice(rs.getInt("PRODUCT_PRICE")); // 상품 가격
					data.setProductCount(rs.getInt("PRODUCT_COUNT")); // 상품재고
					data.setProductBrand(rs.getString("PRODUCT_BRAND")); // 상품 브랜드
					datas.add(data); // 위의 배열에 정보 삽입
				}
			} else if (productDTO.getCondition().equals("ALL_PRICE_DESC")) { // 가격 내림차순
				pstmt = conn.prepareStatement(PRODUCT_ALL_DOWN);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					ProductDTO data = new ProductDTO();
					data.setProductPK(rs.getInt("PRODUCT_PK")); // 상품번호(PK)
					data.setProductName(rs.getString("PRODUCT_NAME")); // 상품 이름
					data.setProductPrice(rs.getInt("PRODUCT_PRICE")); // 상품 가격
					data.setProductCount(rs.getInt("PRODUCT_COUNT")); // 상품재고
					data.setProductBrand(rs.getString("PRODUCT_BRAND")); // 상품 브랜드
					datas.add(data); // 위의 배열에 정보 삽입
				}
			} else if (productDTO.getCondition().equals("ALL_PRICE_ASC")) { // 가격 오름차순
				pstmt = conn.prepareStatement(PRODUCT_ALL_UP);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					ProductDTO data = new ProductDTO();
					data.setProductPK(rs.getInt("PRODUCT_PK")); // 상품번호(PK)
					data.setProductName(rs.getString("PRODUCT_NAME")); // 상품 이름
					data.setProductPrice(rs.getInt("PRODUCT_PRICE")); // 상품 가격
					data.setProductCount(rs.getInt("PRODUCT_COUNT")); // 상품재고
					data.setProductBrand(rs.getString("PRODUCT_BRAND")); // 상품 브랜드
					datas.add(data); // 위의 배열에 정보 삽입
				}
			} else if (productDTO.getCondition().equals("ALL_BRAND")) { // 브랜드별 검색
				pstmt = conn.prepareStatement(PRODUCT_BRAND);
				pstmt.setString(1, productDTO.getProductBrand()); // 브랜드 받아야함
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					ProductDTO data = new ProductDTO();
					data.setProductPK(rs.getInt("PRODUCT_PK")); // 상품번호(PK)
					data.setProductName(rs.getString("PRODUCT_NAME")); // 상품 이름
					data.setProductPrice(rs.getInt("PRODUCT_PRICE")); // 상품 가격
					data.setProductCount(rs.getInt("PRODUCT_COUNT")); // 상품재고
					data.setProductBrand(rs.getString("PRODUCT_BRAND")); // 상품 브랜드
					datas.add(data); // 위의 배열에 정보 삽입
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4 db 연결 해제
			JDBCUtil.disconnect(conn, pstmt);
		}
		return datas;// 데이터 반환
	}

	// 상품 상세보기
	public ProductDTO selectOne(ProductDTO productDTO) {
		ProductDTO data = null; // 찾은 데이터 없으면 null 반환

		// 1,2 드라이버 로드, db연결
		Connection conn = JDBCUtil.connect();

		// 3db 사용
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(PRODUCT_SELECT);
			pstmt.setInt(1, productDTO.getProductPK());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				data.setProductPK(rs.getInt("PRODUCT_PK"));
				data.setProductName(rs.getString("PRODUCT_NAME"));
				data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				data.setProductCount(rs.getInt("PRODUCT_COUNT"));
				data.setProductBrand(rs.getString("PRODUCT_BRAND"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4 db연결 해제
			JDBCUtil.disconnect(conn, pstmt);
		}
		return data;
	}

	// 상품 추가
	public boolean insert(ProductDTO productDTO) {
		// 1,2 드라이버 로드 / db 연결
		Connection conn = JDBCUtil.connect();

		// 3 db 사용
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(INSERT_PRODUCT); // 상품 추가 쿼리
			pstmt.setString(1, productDTO.getProductName()); // 상품이름 설정
			pstmt.setInt(2, productDTO.getProductPrice()); // 상품 가격 설정
			pstmt.setInt(3, productDTO.getProductCount()); // 상품재고 설정
			pstmt.setString(4, productDTO.getProductBrand()); // 상품 브랜드 설정
			int result = pstmt.executeUpdate();
			if (result <= 0) { // 추가 실패시
				return false; // false 반환
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}
		return true; // ㄱㅊ
	}

	// 상품 데이터 업데이트
	public boolean update(ProductDTO productDTO) {

		// 1,2 드라이버 로드 , db연결
		Connection conn = JDBCUtil.connect();

		// 3 db 사용
		PreparedStatement pstmt = null;
		try {
			if (productDTO.getCondition().equals("ADD_PRODUCT")) { // 재고 추가시
				pstmt = conn.prepareStatement(UPDATE_ADD); // 재고 추가 쿼리문
				pstmt.setInt(1, productDTO.getProductCount()); // 재고 추가 수
				pstmt.setInt(2, productDTO.getProductPK()); // 재고 추가할 상품pk
			} else if (productDTO.getCondition().equals("BUY_PRODUCT")) { // 상품 구매시
				pstmt = conn.prepareStatement(UPDATE_BUY); // 상품 재고 빼기 쿼리문
				pstmt.setInt(1, productDTO.getProductCount()); // 재고 빼는 수
				pstmt.setInt(2, productDTO.getProductPK()); // 재고 빼는 상품 pk
			}
			int result = pstmt.executeUpdate();
			if (result <= 0) {
				return false; // 실패시 false 반환
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // 오류 시 false반환
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}
		return true; // true 반환
	}

	// 상품 삭제
	public boolean delete(ProductDTO productDTO) {
		// 1, 2 드라이버 로드, db연결
		Connection conn = JDBCUtil.connect();

		// 3 db 사용
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(DELETE_PRODUCT); // 상품삭제 쿼리문
			pstmt.setInt(1, productDTO.getProductPK()); // 삭제할 상품PK
			int result = pstmt.executeUpdate();
			if (result <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			// db연결 해제
			JDBCUtil.disconnect(conn, pstmt);
		}
		return true;
	}

}
