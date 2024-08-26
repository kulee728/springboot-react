import {useEffect,useState} from 'react';
import axios from 'axios';
import './AirPollutionData.css';

const AirPollutionData = ()=>{/*
    const [msg,setMsg] = useState('');

    useEffect(()=>{
       axios.get("/api/air-pollution")
       .then(response=>{
        console.log("response :",response);
        setMsg(response.data);
       })
       .catch(err=>{
        console.log('error!',err);
    })
    },[])*/
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        // API 호출
        axios.get('http://localhost:8081/api/air-pollution')
            .then((response) => {
                const parser = new DOMParser();
                const xml = parser.parseFromString(response.data, "text/xml");
                const items = xml.getElementsByTagName("item");

                const parsedData = Array.from(items).map(item => {
                    const fullDateTime = item.getElementsByTagName("dataTime")[0]?.textContent;
                    const yearMonth = fullDateTime ? fullDateTime.slice(0, 7) : 'N/A';
                    console.log("yearMonth :",yearMonth);
                    console.log("fullDateTime :",fullDateTime);
                    return {
                        stationName: item.getElementsByTagName("stationName")[0]?.textContent,
                        so2Grade: item.getElementsByTagName("so2Grade")[0]?.textContent,
                        coFlag: item.getElementsByTagName("coFlag")[0]?.textContent,
                        khaiValue: item.getElementsByTagName("khaiValue")[0]?.textContent,
                        pm10Value: item.getElementsByTagName("pm10Value")[0]?.textContent,
                        no2Value: item.getElementsByTagName("no2Value")[0]?.textContent,
                        o3Value: item.getElementsByTagName("o3Value")[0]?.textContent,
                        dateTime:fullDateTime,
                        isClicked : false
                    };
                });
                console.log("data is :",parsedData);
                
                setData(parsedData);
                setLoading(false);
            })
            .catch((error) => {
                setError(error);
                setLoading(false);
            });
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error.message}</div>;
    }

    const handleToggleClick=(index)=>{
        const updatedData = data.map((item, i) =>
            i === index ? { ...item, isClicked: !item.isClicked } : item
        );
        setData(updatedData);
    }

    return(
        <>
            <h1 className='heading'>공공 대기 오염 데이터 조회</h1>
            <div className='card-container'>
                {data.map((item,index)=>(
                    <div key={index} className='card'>
                        <h2 className='stationName' onClick={()=>handleToggleClick(index)}>
                            {item.stationName}
                        </h2>
                        {item.isClicked && (
                            <>
                            <p><strong>-- {item.dateTime} 기준 -- </strong></p>
                            <p><strong>이산화황(SO2) 등급 : {item.so2Grade}</strong></p>
                            <p><strong>일산화탄소(CO) 척도 : {item.coFlag}</strong></p>
                            <p><strong>종합대기질 지수 : {item.khaiValue}</strong></p>
                            <p><strong>PM10 지수 : {item.pm10Value}</strong></p>
                            <p><strong>이산화질소(NO2) 지수 : {item.no2Value}</strong></p>
                            <p><strong>오존 지수 : {item.o3Value}</strong></p>
                            </>
                        )}
                    </div>
                ))}
            </div>
        </>
    )
}
export default AirPollutionData;
