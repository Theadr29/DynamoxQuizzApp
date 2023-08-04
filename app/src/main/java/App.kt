import android.app.Application
import br.com.AdrianoDev.dynamoxquiz.Model.AppDataBase

class App : Application() {

    lateinit var db: AppDataBase

    override fun onCreate() {
        super.onCreate()
        db = AppDataBase.getDataBase(this)
    }

}
