<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!-- Footer -->
<footer>
	<sec:authorize access="!hasAnyRole('ADMIN','BANK')">
		<div class="footer-above">
			<div class="container">
				<div class="row">
					<div class="footer-col col-md-3">
						<h4>Our Contact</h4>
						<p>
							New Village <br>Gurgaon, IN 122001
						</p>
					</div>
					<div class="footer-col col-md-3">
						<h4>Quick Links</h4>
					</div>
					<div class="footer-col col-md-3">
						<h4>Hot Deals</h4>
						<p>Deals</p>
					</div>
					<div class="footer-col col-md-3">
						<h4>About Trump Card</h4>
						<p>This is a portal for every deal available on your cards</p>
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