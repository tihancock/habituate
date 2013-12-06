(ns org.habituate.main
  (:use [neko.activity :only [defactivity set-content-view!]]
        [neko.threading :only [on-ui]]
        [neko.ui :only [make-ui]])
  (:import [android.content Intent]
           [android.app Activity]))

(def add-habit-layout
  [:linear-layout {}
   [:edit-text {:hint "Habit"
                :id ::habit}]])

(defactivity org.habituate.AddHabit
  :on-create
  (fn [this bundle]
    (on-ui
     (set-content-view! addHabit (make-ui add-habit-layout)))))

(defn add-habit
  [^Activity context]
  (let [intent (Intent. context (Class/forName "org.habituate.AddHabit"))]
    (.startActivity context intent)))

(defn main-layout
  [context]
  [:linear-layout {}
   [:button {:text "Add Habit"
             :on-click (fn [_] (add-habit context))}]])

(defactivity org.habituate.MainActivity
  :on-create
  (fn [this bundle]
    (on-ui
     (set-content-view! mainActivity (make-ui (main-layout this))))))
