<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recruta B2W</title>
    <link rel="stylesheet" href="./css/global.css">
    <link rel="stylesheet" href="./css/login.css">
    <link href="./img/B2W_Digital_logo.png" rel="icon" type="image/x-icon" />

</head>

<body>

    <header>
        <figure>
            <a href="index.jsp"><img src="./img/B2W_Digital_logo.png" class="logo" alt=""></a>
        </figure>
        <span class="mobile-btn"></span>
        <nav class="menu-nav mobile-menu">
        </nav>
    </header>
    <div class="general">
        <div class="login">
            <h2>Login de Acesso</h2>
            <form action="login" method="post">
                <div class="field">
                    <label for="idmail">E-mail:*</label>
                    <input type="text" name="cpmail" id="IDMAIL" placeholder="Digite seu e-mail" required="required">
                </div>

                <div class="field">
                    <label for="idpass">Senha:*</label>
                    <div class="input-password">
                        <input type="password" name="cppass" id="pass" placeholder="Digite sua senha">
                        <img src="./img/olho.webp" id="olho" class="olho">
                    </div>
                </div>
                
                 <div class="field erro">
                 	<p>${msgErro}</p>
                 </div>
                
                <div class="acessar">
                    <input type="submit" value="Acessar" class="btn-acessar">
                </div>
            </form>
        </div>
    </div>
    <footer>
        <h1>RecrutaB2W</h1>
        <h1>1TDST - NextStep - FIAP</h1>
    </footer>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/login.js"></script>
</body>

</html>