MOCK PROJECT 

* Em viết cái này để coi như là khung sườn của project mình, rồi từ đó mình viết/ phát triển thêm

* Trong này có gì?
	- Các Entity: Đã mapped theo đề bài và có một số chỉnh sửa mà em nghĩ là hợp lý (chi tiết bên dưới).
	- Controller: Chỉ mới có controller cho trang login.
	- Security: Đã setup security cơ bản. Phải đăng nhập mới dùng được. Chưa phân quyền chi tiết (FA Manager, Admin, Trainer,...).
			Em cũng đã setup một LoginFailureHandler và LoginSuccessHandler để xử lý sau đăng nhập.
	- Interceptor: Đã tạo và khai báo sẵn 1 interceptor demo cho mọi người để khi cần dùng thì lấy cái đó dùng luôn hoặc viết thêm.
	- Repository: Chỉ mới có Repo của Role và User để phục vụ cho phần Security.
	- Service: Chỉ mới có Service cho Security và User
	- Utils: Một vài utils cho web (lưu session, cookie,...).
		HibernateValidator để validate các Entity, bên trong là 2 method đề validate (trả về boolean hoặc message).
		StringValidateUtils để kiểm tra String có rỗng hay null không (để validate sơ sơ input trên web).
		DateUtils để tiện chuyển giữa sql.Date và Java util.Date.
	- Resources: Có template trang login như đề bài
			Em có chia thành các cụm nhỏ (thanh nav, sidebar, footer,...) để mọi người gắn vào trang thymeleaf
			cho nó gọn code.

* Em viết theo kiểu gì?
	- Em viết chủ yếu dựa trên các demo của anh Dương để dùng các module như security, repo,... sao cho tiện (với em =))) ),
		mọi người thấy khó hiểu thì có thể cùng nhau sửa cho nó thống nhất ha.
	- Phần User, phân quyền em làm như sau: các Class/Entity Trainer, ClassAdmin, FAManager, FARec, DeliveryManager đều extends class User.
						Trong đó, các class kia giữ nguyên các field của nó, class User thì chứa role, username, password.
						Vì Srping Security trả về/xử lý một Object là instance của class UserDetails thuộc Spring 
						nên em nghĩ việc cho các class kia extends class User có thể làm cho code gọn hơn, đỡ phức tạp sau này.
	- Em viết sẵn các user để đăng nhập rồi, các user có các role khác nhau, khi đăng nhập console nó log ra cho mọi người check
	- Tên đăng nhập và mật khẩu mọi người xem trong phần Application (trong package com.example.demo)

* Em thay đổi gì?
	- Đổi một số kiểu dữ liệu và quan hệ của các Entity lại cho nó hợp lý hơn (em nghĩ vậy)
	- Cụ thể là đổi một số trường gender, phone, email, comment, note, status, name, level, technology,...thành kiểu String trong java 
		và kiểu dữ liệu tương ứng trong database.
	- Còn một số trường mà em còn mù mờ về ý nghĩa như FSU, relatedParty,.. em để nguyên kiểu Integer như đề bài. Trong lúc làm nếu
		mọi người phát hiện được kiểu dữ liệu chính xác thì thay đổi ha.
	- Thêm quan hệ đến Trainer cho các Entity như Interview, InterviewValuation, Audit,... và tương tự với các entity có liên quan đến
		ClassBatch và Trainee. Em thêm ko nhiều đâu, mọi người nhìn vào đề bài thì cũng tự hiểu phải thêm hà.
	- Thêm các Entity/Class FAManager, FARec, DeliveryManager cho khớp yêu cầu của anh Dương.

* Em dùng các tool gì?
	- Em dùng hibernate để map.
	- Dùng database là SQLServer. Nhưng em có để config cho MySQl ở file application.properties . Mọi người có dùng MySQL
		thì chịu khó sửa lại một tí phần khai báo kiểu dữ liệu ha. (em có khai báo MySQl trong file pom luôn rồi).
	- Dùng lombok để cho nó tự generate các constructor, getter, setter.

* Làm sao để chạy project?
	- Em để sẵn mode là create rồi, với  sẵn code để tạo vài user trong file Application luôn.
	- Mọi người có chạy lần hai hay muốn giữ database thì đổi lại thành update và comment code trong Application nha.
	- Trước tiên mọi người install lombok nha, nếu ko nó sẽ báo thiếu các method getter, setter,.... Làm như thế này:
		- Mở phần Maven Dependencies trong Project Explorer ra
		- Tìm tới lombok-1.18-22.jar
		- Click phải -> Run as -> Java Application
		- Trong giao diện setup mọi người trỏ đến file .exe của Eclipse hoặc Spring Tool Suite (cái mọi người đang dùng), rồi cho nó install.
		- Install xong thì mọi người tắt Eclipse hoặc STS đi rồi mở lại, lúc này build project sẽ ko lỗi nữa (nhớ chạy Maven Install nha).
	- Chạy Maven install.
	- Run as Spring Application
	- Chúc may mắn.
* Em biết là code này chưa hoàn chỉnh đâu nhưng mà em hiểu là mọi người bận đi làm nên không có nhiều thời gian làm. Em muốn viết để mình
	có cái bám theo làm cho nó dễ. Có gì khó hiểu thì mọi người hỏi em ha. Còn thấy code này dở thì nói lun để sửa cho nhóm làm ha.

* Cảm ơn mọi người.