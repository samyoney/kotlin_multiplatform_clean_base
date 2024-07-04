import SwiftUI
import Shared

let appText = iOSApp.appText

@main
struct iOSApp: App {
    
    @LazyKoin static var appText: AppText

    @StateObject private var router: SwiftAppRouter = SwiftAppRouter()

    var body: some Scene {
        WindowGroup {
            SwiftAppView(router: router) {
                router.getRootview()
            }
        }
    }
}
