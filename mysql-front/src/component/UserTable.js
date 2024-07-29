import React from 'react';
import axios from 'axios';
/*
()=>{ } : 단독으로 기능을 진행 (no param)
(value) => { } : 특정 값에 영향을 받아 기능이 변화할 때 사용
*/

const UserTable=({users,deleteUser,editUser})=>{

    return(
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {users.map(user=>(
                    <tr key={user.id}>
                        <td>{user.id}</td>
                        <td>{user.name}</td>
                        <td>{user.email}</td>
                        <td><button onClick={()=>deleteUser(user.id)}>유저 삭제하기</button></td>
                        <td><button onClick={()=>editUser()}>정보 수정하기</button></td>
                    </tr>

                ))}
            </tbody>
        </table>
    )
}

export default UserTable;