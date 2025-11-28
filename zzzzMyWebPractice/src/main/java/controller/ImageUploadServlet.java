package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/ImageUploadServlet")
@MultipartConfig
public class ImageUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ImageUploadServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[로그] ImageUploadServlet");
		
		// 1) CKEditor에서 보내는 파일 파트 받기 (필드 이름: upload)
        Part filePart = request.getPart("upload");

        // 2) 파일 이름 (원본 이름 그대로 사용)
        String fileName = Paths.get(filePart.getSubmittedFileName())	
                               .getFileName()
                               .toString();

        // 3) 실제 저장할 경로 (webapp/upload 폴더)
        String uploadPath = request.getServletContext().getRealPath("/upload");
        System.out.println(uploadPath);
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 4) 파일 저장
        filePart.write(uploadPath + File.separator + fileName);

        // 5) 클라이언트에서 접근할 수 있는 URL (img src 값)
        String fileUrl = request.getContextPath() + "/upload/" + fileName;

        // 6) CKEditor simpleUpload가 기대하는 JSON 형태로 응답
        response.setContentType("application/json; charset=UTF-8");
        System.out.println("[로그] 파일 저장 경로 : " + fileUrl);
        response.getWriter().write("{\"url\":\"" + fileUrl + "\"}");
	}

}
