import { Outlet } from 'react-router-dom'
import Navbar from '../components/Navbar'
import Sidebar from '../components/Sidebar'

function MainLayout() {
    return (
        <div>
            <Navbar />

            <div style={{ display: 'flex' }}>
                <Sidebar />

                <main style={{ padding: '20px', flex: 1 }}>
                    <Outlet />
                </main>
            </div>
        </div>
    )
}

export default MainLayout