import "./styles.css";
import { ReactComponent as Logo } from "../../assets/icons/logo.svg";
import { ReactComponent as QuestioSquare } from "../../assets/icons/question-square.svg";

const index = () => {
  return (
    <header>
      <nav className="nav-container">
        <div className="infraction-nav-content">
          <a href="/" className="infraction-nav-link">
            <Logo className="infraction-logo" />
            <p className="infraction-title">InfraTraker</p>
          </a>
        </div>
        <div className="infraction-buttons">
          <a href="/about" className="infraction-about">
            <QuestioSquare className="infraction-about-icon" />
          </a>
        </div>
      </nav>
    </header>
  );
};

export default index;
