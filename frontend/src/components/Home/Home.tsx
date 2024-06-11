import { useEffect, useState } from "react";
import {fetchProblems} from "../../api/problems";
import { Problem } from "../../types/Api";
import Card from "../Card/Card";
import "./Home.css"
import Filter from "../Filter/Filter";


const Home:React.FC = () => {
    const [problems, setProblems] = useState<Problem[]>([]);
    const [filteredProblems, setFilteredProblems] = useState<Problem[]>([]);
    const [error, setError] = useState<string | null>(null);
    const [loading, setLoading] = useState<boolean>(true);

    useEffect(() => {
        const getProblems = async () => {
        try {
            const problemData = await fetchProblems();
            const filtered = problemData.filter(x => x.size === 3);
            setProblems(filtered);
        } catch (err) {
            setError('Error fetching problems data');
            console.error(err);
        } finally {
            setLoading(false);
        }
        };

    getProblems();
  }, []);

    const filterData = async (size: number): Promise<Problem[]> => {
        const data = await fetchProblems();
        
        const filtered = data.filter(x => x.size === size)
        setProblems(filtered)
        return filtered;
    }

    return (
        <div >
            <Filter onclick={filterData}/>
            <div className="home">
                {problems.map(x => <Card matrix={x.description} size={x.size} key={x.id} id={x.id}/>)}
            </div>
        </div>
    )
}

export default Home;