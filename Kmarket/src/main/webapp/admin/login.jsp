<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="./_loginHeader.jsp"></jsp:include> 
<main id="member">
    <div class="login">
        <nav>
            <h1>로그인</h1>
            <p>
                HOME > 
                <strong>로그인</strong>
            </p>
        </nav>
        <form action="/Kmarket/admin/login.do" method="post">
            <table border="0">
                <tr>
                    <td>아이디</td>
                    <td>
                        <input type="text" name="uid" placeholder="아이디 입력">
                    </td>
                </tr>
                <tr>
                    <td>비밀번호</td>
                    <td>
                        <input type="password" name="pass" placeholder="비밀번호 입력">
                    </td>
                </tr>
            </table>
            <input type="submit" value="로그인">
            
        </form>
    </div>
</main>
<jsp:include page="./_footer.jsp"></jsp:include>