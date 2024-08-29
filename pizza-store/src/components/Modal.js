

const Modal = ({isModal,onClose,children})=>{

    if(!isModal){
        return null;
    }
    return(
        <div>
            <div>
                <button onClick={onClose}>모달 닫기</button>
            </div>
            {children}
        </div>
    )
}
export default Modal;