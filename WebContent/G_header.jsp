<%@page contentType="text/html; charset=UTF-8" %>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-sccle=1">
    <style>
    body {
    height: 100vh;
    display: flex;
    background-color: rgb(118, 184, 206);
    margin: 0;
    font-family: "Futura", Helvetica, sans-serif;
	}

	.logo {
	    font-size: 24px;
	}


	/* Navbar & Navmenu color */
	:root {
	    --background-navbar: rgba(55, 55, 55, 0.98);
	}

	.header {
	    background: var(--background-navbar);
	    position: fixed;
	    width: 100%;
	    height: 52px;
	}

	/* Nav items */
	.menu {
	    list-style: none;
	    position: absolute;
	    width: 100%;
	    height: auto;
	    top: 0;
	    margin-top: 52px;
	    padding: 0 0 10px 0;
	    clear: both;
	    background: var(--background-navbar);
	    transition: 0.3192s cubic-bezier(0.04, 0.04, 0.12, 0.96) 0.1008s;
	    transform: scale(1, 0);
	    transform-origin: top;
	}

	/* Hamburger menu button */
	.menu-btn:checked ~ .menu {
	    transform: scale(1, 1);
	    transform-origin: top;
	    transition: 0.3192s cubic-bezier(0.04, 0.04, 0.12, 0.96) 0.1008s;
	}

	/* Hamburger menbu text */
	.menu a {
	    text-decoration: none;
	    font-weight: 500;
	    letter-spacing: 2px;
	    font-size: 16px;
	    text-transform: capitalize;
	    color: #ddd;
	    opacity: 0;
	    transition: 0.5s;
	}

	.menu li {
	    border-top: 1px solid rgb(75, 75, 75);
	    padding: 15px 0;
	    margin: 0 54px;
	    opacity: 0;
	    transition: 0.5s;
	}

	.menu-btn:checked ~ .menu a,
	.menu-btn:checked ~ .menu li {
	    opacity: 1;
	    transition: 0.3192s cubic-bezier(0.04, 0.04, 0.12, 0.96) 0.2s;
	}

	.menu-btn {
	    display: none;
	}

	.menu-icon {
	    display: inline-block;
	    position: relative;
	    cursor: pointer;
	    padding: 24px 14px;
	    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
	}

	.navicon {
	    background: #ddd;
	    display: block;
	    height: 3px;
	    width: 26px;
	    position: relative;
	    transition: 0.3192s cubic-bezier(0.04, 0.04, 0.12, 0.96) 0.1008s;
	}

	.navicon:before,
	.navicon:after {
	    content: "";
	    display: block;
	    height: 100%;
	    width: 100%;
	    position: absolute;
	    background: #ddd;
	    transition: 0.3192s cubic-bezier(0.04, 0.04, 0.12, 0.96) 0.1008s;
	}

	.navicon:before {
	    top: 9px;
	}

	.navicon:after {
	    bottom: 9px;
	}

	/* Hamburger Menu Animation Start */
	.menu-btn:checked ~ .menu-icon .navicon:before {
	    transform: rotate(-45deg);
	}

	.menu-btn:checked ~ .menu-icon .navicon:after {
	    transform: rotate(45deg);
	}

	.menu-btn:checked ~ .menu-icon:not(.steps) .navicon:before {
	    top: 0;
	}

	.menu-btn:checked ~ .menu-icon:not(.steps) .navicon:after {
	    bottom: 0;
	}

	.menu-btn:checked ~ .menu-icon .navicon {
	    background: rgba(0, 0, 0, 0);
	    transition: 0.2192s cubic-bezier(0.04, 0.04, 0.12, 0.96) 0.1008s;
	}
	/* Hamburger Menu Animation End */

	/* Navbar Container */
	.navtext-container {
	    width: 100%;
	    height: 52px;
	    position: absolute;
	    box-sizing: border-box;
	    display: flex;
	    justify-content: center;
	    align-items: center;
	}

	/* Navbar Text */
	.navtext {
	    position: absolute;
	    text-transform: uppercase;
	    color: #ddd;
	    letter-spacing: 4px;
	    font-size: 20px;
	}
    </style>
</head>

<header class="header">
	<div class="navtext-container">
	<div class="navtext">すてっぷのーと</div>
	</div>
    <input type="checkbox" class="menu-btn" id="menu-btn">
    <label for="menu-btn" class="menu-icon"><span class="navicon"></span></label>
    <ul class="menu">
      <li class="top"><a href="#">生活記録</a></li>
      <li><a href="#">連絡帳</a></li>
      <li><a href="#">全体掲示板</a></li>
      <li><a href="#">欠席連絡</a></li>
      <li><a href="#">ログアウト</a></li>
    </ul>

</header>