import "./styles.css";

const index = () => {
  const data = new Date();
  const ano = data.getFullYear();

  return (
    <footer>
      <div className="footer-container">
        <p className="copy-paragraph">
          yuriidiiego ©{ano} - Todos os direitos reservados
        </p>
      </div>
    </footer>
  );
};

export default index;
