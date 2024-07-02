import SwiftUI
import Shared

let appText = iOSApp.appText

@main
struct iOSApp: App {
    @LazyKoin static var appText: AppText

    @StateObject private var router: SwiftAppRouter = SwiftAppRouter()
    @StateObject var viewModel = SharedViewModel<SplashViewModel>()

    var body: some Scene {
        WindowGroup {
//            SwiftAppView(router: router) {
                SplashView()
//            }
        }
    }
}
