import { Infraction } from "types/infraction";
import "./styles.css";

type Props = {
  infractions: Infraction[];
};

const index = ({ infractions }: Props) => {
  return (
    <div className="infraction-sheet-container">
      <table className="table table-striped">
        <thead className="thead-dark">
          <tr>
            <th scope="col">#</th>
            <th scope="col">Data</th>
            <th scope="col">Velocidade</th>
            <th scope="col">Placa</th>
            <th scope="col">Tipo do Veiculo</th>
          </tr>
        </thead>
        <tbody>
          {infractions.map((infraction, index) => (
            <tr key={index}>
              <th scope="row">{infraction.id}</th>
              <td>{infraction.createdAt}</td>
              <td>{infraction.speed}km/h</td>
              <td>{infraction.licensePlate}</td>
              <td>{infraction.vehicleType}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default index;
