<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Footer -->
<footer>
	<sec:authorize access="!hasAnyRole('ADMIN','BANK')">
		<div class="footer-above">
			<div class="container">
				<div class="row">
					<div class="footer-col col-md-2">
						<h4>Our Contact</h4>
						<p>
							New Village <br>Gurgaon<br>Haryana, IN 122001<br><i class="fa fa-phone"></i>+91 999 999 9999
						</p>
						<p><a href='<c:url value="mailTo:trumpcard@support.com" />'>Write to Us!</a></p>
					</div>
					<div class="footer-col col-md-2 hidden-xs hidden-sm">
						<h4>Stores</h4>
						<div class="footer-store"></div>
					</div>
					<div class="footer-col col-md-2 hidden-xs hidden-sm">
						<h4>Banks</h4>
						<div class="footer-bank"></div>
					</div>
					<div class="footer-col col-md-2 hidden-xs hidden-sm">
						<h4>Cards</h4>
						<div class="footer-card"></div>
					</div>
					<div class="footer-col col-md-2 hidden-xs hidden-sm">
						<h4>Categories</h4>
						<div class="footer-category"></div>
					</div>
					<div class="footer-col col-md-2">
						<h4>About Trump Card</h4>
						<p><a href='<c:url value="/page/aboutUs" />'>Our Journey</a></p>
						<p><a href='<c:url value="/page/termsOfUse" />'>Terms of Use</a></p>
						<p><a href='<c:url value="/page/privacy" />'>Privacy Policy</a></p>
					</div>
				</div>
			</div>
		</div>
	</sec:authorize>
	<div class="footer-below">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-sm-6 copyright">Copyright &copy; CARD
					DEALS 2016</div>
				<div class="col-md-6 col-sm-6">
					<ul class="list-inline">
						<li><a href="#" class="btn-social btn-outline"><i
								class="fa fa-fw fa-facebook"></i></a></li>
						<li><a href="#" class="btn-social btn-outline"><i
								class="fa fa-fw fa-google-plus"></i></a></li>
						<li><a href="#" class="btn-social btn-outline"><i
								class="fa fa-fw fa-twitter"></i></a></li>
						<li><a href="#" class="btn-social btn-outline"><i
								class="fa fa-fw fa-linkedin"></i></a></li>
						<li><a href="#" class="btn-social btn-outline"><i
								class="fa fa-fw fa-dribbble"></i></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</footer>