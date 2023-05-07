import "./styles.css";
import { ReactComponent as Logo } from "../../assets/icons/logo.svg";

const index = () => {
  return (
    <div className="about-container">
      <div className="about-section">
        <p>
          <span>InfraTracker </span>é uma solução inovadora que oferece um{" "}
          <span>gerenciamento </span>
          <br /> eficiente e abrangente de registros de infrações de{" "}
          <span>trânsito</span>, em todo território brasileiro.
          <br /> Estamos aqui para ajudar, então, se você tiver alguma dúvida ou
          encontrar qualquer problema, <br />
          não hesite em entrar em contato conosco.
        </p>
        <Logo className="about-logo" />
      </div>
      <div className="about-social-links">
        <a href="/" className="about-links">
          <i className="bi bi-house-fill"></i>
        </a>
        <a
          target="_blank"
          href="https://www.linkedin.com/in/yuriidiiego"
          className="about-links"
        >
          <i className="bi bi-linkedin"></i>
        </a>
        <a
          target="_blank"
          href="https://github.com/yuriidiiego"
          className="about-links"
        >
          <i className="bi bi-github"></i>
        </a>
      </div>
    </div>
  );
};

export default index;
