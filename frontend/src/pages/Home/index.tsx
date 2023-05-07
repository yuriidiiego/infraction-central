import axios from "axios";
import React from "react";
import InfractionSheet from "../../components/InfractionSheet/index";
import { BASE_URL } from "../../utils/requests";
import { Infraction } from "types/infraction";

const index = () => {
  const [infractions, setInfractions] = React.useState<Infraction[]>([]);

  React.useEffect(() => {
    axios
      .get(`${BASE_URL}/records`)
      .then((res) => {
        const data = res.data;
        setInfractions(data);
      })
      .catch((err) => console.log(err));
  }, [infractions]);

  return (
    <>
      <InfractionSheet infractions={infractions} />
    </>
  );
};

export default index;
